package joliex.execCommand;

import jsdt.core.types.BasicType;
import jolie.runtime.Value;

public class CommandExecutionRequest_stdoutStreamOperation extends BasicType<String> {

    public CommandExecutionRequest_stdoutStreamOperation(String root) {
        super(root);
    }

    public static CommandExecutionRequest_stdoutStreamOperation parse(Value value) {
        if (value != null && value.isString()) {
            return new CommandExecutionRequest_stdoutStreamOperation(value.strValue());
        } else {
            return null;
        }
    }

    public Value toValue() {
        Value value = super.toValue();
        return value;
    }
}
