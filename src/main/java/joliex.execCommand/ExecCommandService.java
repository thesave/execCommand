/*
 * Copyright (C) 2021 by Saverio Giallorenzo <saverio.giallorenzo@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Library General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this program; if not, write to the
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * For details about the authors of this software, see the AUTHORS file.
 */

package joliex.execCommand;

import jolie.net.CommMessage;
import jolie.runtime.JavaService;
import jolie.runtime.Value;

import java.io.*;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecCommandService extends JavaService {

	public void start( Value value ) {
		CommandExecutionRequest request = CommandExecutionRequest.parse( value );
		List< String > command = new LinkedList<>();
		request.args().get().stream().filter( Optional::isPresent ).map( Optional::get ).forEach( e -> command.add( e.root() ) );
		ProcessBuilder processBuilder = new ProcessBuilder( command );
		request.workingDirectory().get().ifPresent( dir -> processBuilder.directory( Path.of( dir.root() ).toFile() ) );
		ExecutorService executor = Executors.newSingleThreadExecutor();
		try {
			Process process = processBuilder.start();
			CompletableFuture< Process > onExit = process.onExit();
			request.stdoutStreamOperation().get().ifPresent( operation -> {
				BufferedReader stdout = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
				executor.submit( () -> {
					try {
						String line = stdout.readLine();
						while ( line != null ) {
							sendMessage( CommMessage.createRequest( operation.root(), "/", Value.create( line ) ) );
							line = stdout.readLine();
						}
					} catch ( IOException e ) {
						e.printStackTrace();
					}
				} );
			} );
			request.stderrStreamOperation().get().ifPresent( operation -> {
				BufferedReader stderr = new BufferedReader( new InputStreamReader( process.getErrorStream() ) );
				executor.submit( () -> {
					try {
						String line = stderr.readLine();
						while ( line != null ) {
							sendMessage( CommMessage.createRequest( operation.root(), "/", Value.create( line ) ) );
							line = stderr.readLine();
						}
					} catch ( IOException e ) {
						e.printStackTrace();
					}
				} );
			} );
			Process terminatedProcess = onExit.get();
			executor.shutdown();
			if ( request.terminationOperation().get().isPresent() ) {
        sendMessage( CommMessage.createRequest(
                request.terminationOperation().get().get().root(), "/", Value.create( terminatedProcess.exitValue() ) )
        );
			}
		} catch ( IOException | ExecutionException | InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
