from @jolie.execCommand.main import ExecCommand
from console import Console

service main(){

  inputPort IN {
    location: "local"
    oneWay: stdErr, stdOut, exitNotification
  }

  embed ExecCommand as ExecCommand
  embed Console as Console

  execution { concurrent }

  init {
    with( request ){
      .args[#.args] = "ping"
      .args[#.args] = "-c"
      .args[#.args] = "5"
      .args[#.args] = "localhost"
      .stderrStreamOperation = "stdErr"
      .stdoutStreamOperation = "stdOut"
      .terminationOperation = "exitNotification"
    }
    start@ExecCommand( request )
  }

  main {
    [ stdErr( message ) ]{
      println@Console( "ERROR: " + message )()
    }
    [ stdOut( message ) ]{
      println@Console( "STDOUT: " + message )()
    }
    [ exitNotification( exitCode ) ]{
      println@Console( "Command terminated with exit code" + exitCode )()
      exit
    }
  }

}