type CommandExecutionRequest: void {
    .args*: string
    .workingDirectory?: string
    .stderrStreamOperation?: string
    .stdoutStreamOperation?: string
    .terminationOperation?: string
}

// if invoked, the terminationOperation will receive a int representing the exitCode of the process execution
type ExitCode: int

interface ExecCommandInterface {
  oneWay: start( CommandExecutionRequest )
}

service ExecCommand {
  inputPort IP {
    location: "local"
    interfaces: ExecCommandInterface
  }

  foreign java {
    class: "joliex.execCommand.ExecCommandService"
  }
}