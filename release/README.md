# execCommand

A Jolie library to execute a command with arguments in a separate process in an asynchronous way.

It currently relies on the [Runtime](https://docs.oracle.com/javase/7/docs/api/java/lang/Runtime.html) standard Java library and it is mainly an asynchronous/reactive take on the standard Jolie [Exec](https://docs.jolie-lang.org/v1.10.x/language-tools-and-standard-library/standard-library-api/exec.html) library, where all commands are issued asynchronously and the user can monitor their execution by receiving messages from the standard output and error of the process and well as its termination with the related exit code.

## Roadmap

- [ ] support multiple process executions with correlation sets
- [ ] support process termination via process ids