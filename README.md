# execCommand

A Jolie library to execute a command with arguments in a separate process in an asynchronous way.

It currently relies on the [Process](https://docs.oracle.com/javase/7/docs/api/java/lang/Process.html) standard Java library and it is mainly an asynchronous/reactive take on the standard Jolie [Exec](https://docs.jolie-lang.org/v1.10.x/language-tools-and-standard-library/standard-library-api/exec.html) library, where all commands are issued asynchronously and the user can monitor their execution by receiving messages from the standard output and error of the process, as well as the notification of its termination with the related exit code.

## Installation

`jpm add @thesave/exec_command`

## Usage Example

```jolie
from @thesave.exec_command.main import ExecCommand
from console import Console

service main(){

  inputPort IN {
    location: "local"
    oneWay: stdErr, stdOut, exitNotification
  }

  embed ExecCommand as ExecCommand
  embed Console as Console

  execution { concurrent }

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
```

## Roadmap

- [ ] support multiple process executions with correlation sets
- [ ] support process kill-signal via process ids
