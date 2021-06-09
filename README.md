# execCommand

A Jolie library to execute a command with arguments in a separate process.

It currently relies on the [Runtime](https://docs.oracle.com/javase/7/docs/api/java/lang/Runtime.html) standard Java library and it is mainly an extension of the standard Jolie [Exec](https://docs.jolie-lang.org/v1.10.x/language-tools-and-standard-library/standard-library-api/exec.html) library to stream the standard and error outputs back to the calling service while the command is running.
