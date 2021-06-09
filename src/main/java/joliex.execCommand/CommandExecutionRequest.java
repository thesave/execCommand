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

import jsdt.core.types.BasicType;
import jolie.runtime.Value;
import jsdt.core.cardinality.Multi;
import java.util.stream.Collectors;
import jsdt.core.cardinality.MaybeSingle;

public class CommandExecutionRequest extends BasicType<Void> {

    public CommandExecutionRequest(Multi<CommandExecutionRequest_args> args, MaybeSingle<CommandExecutionRequest_workingDirectory> workingDirectory, MaybeSingle<CommandExecutionRequest_stderrStreamOperation> stderrStreamOperation, MaybeSingle<CommandExecutionRequest_stdoutStreamOperation> stdoutStreamOperation, MaybeSingle<CommandExecutionRequest_terminationOperation> terminationOperation) {
        super(null);
        this.args = args;
        this.workingDirectory = workingDirectory;
        this.stderrStreamOperation = stderrStreamOperation;
        this.stdoutStreamOperation = stdoutStreamOperation;
        this.terminationOperation = terminationOperation;
    }

    public static CommandExecutionRequest parse(Value value) {
        if (value != null) {
            Multi<CommandExecutionRequest_args> args = Multi.of(value.getChildren("args").stream().map(CommandExecutionRequest_args::parse).collect(Collectors.toList()));
            MaybeSingle<CommandExecutionRequest_workingDirectory> workingDirectory = MaybeSingle.of(CommandExecutionRequest_workingDirectory.parse(value.getChildren("workingDirectory").get(0)));
            MaybeSingle<CommandExecutionRequest_stderrStreamOperation> stderrStreamOperation = MaybeSingle.of(CommandExecutionRequest_stderrStreamOperation.parse(value.getChildren("stderrStreamOperation").get(0)));
            MaybeSingle<CommandExecutionRequest_stdoutStreamOperation> stdoutStreamOperation = MaybeSingle.of(CommandExecutionRequest_stdoutStreamOperation.parse(value.getChildren("stdoutStreamOperation").get(0)));
            MaybeSingle<CommandExecutionRequest_terminationOperation> terminationOperation = MaybeSingle.of(CommandExecutionRequest_terminationOperation.parse(value.getChildren("terminationOperation").get(0)));
            return new CommandExecutionRequest(args, workingDirectory, stderrStreamOperation, stdoutStreamOperation, terminationOperation);
        } else {
            return null;
        }
    }

    public Value toValue() {
        Value value = super.toValue();
        this.args().addChildenIfNotEmpty("args", value);
        this.workingDirectory().addChildenIfNotEmpty("workingDirectory", value);
        this.stderrStreamOperation().addChildenIfNotEmpty("stderrStreamOperation", value);
        this.stdoutStreamOperation().addChildenIfNotEmpty("stdoutStreamOperation", value);
        this.terminationOperation().addChildenIfNotEmpty("terminationOperation", value);
        return value;
    }

    private final Multi<CommandExecutionRequest_args> args;

    public Multi<CommandExecutionRequest_args> args() {
        return args;
    }

    private final MaybeSingle<CommandExecutionRequest_workingDirectory> workingDirectory;

    public MaybeSingle<CommandExecutionRequest_workingDirectory> workingDirectory() {
        return workingDirectory;
    }

    private final MaybeSingle<CommandExecutionRequest_stderrStreamOperation> stderrStreamOperation;

    public MaybeSingle<CommandExecutionRequest_stderrStreamOperation> stderrStreamOperation() {
        return stderrStreamOperation;
    }

    private final MaybeSingle<CommandExecutionRequest_stdoutStreamOperation> stdoutStreamOperation;

    public MaybeSingle<CommandExecutionRequest_stdoutStreamOperation> stdoutStreamOperation() {
        return stdoutStreamOperation;
    }

    private final MaybeSingle<CommandExecutionRequest_terminationOperation> terminationOperation;

    public MaybeSingle<CommandExecutionRequest_terminationOperation> terminationOperation() {
        return terminationOperation;
    }
}
