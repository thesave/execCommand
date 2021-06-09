package joliex.execCommand;

import jsdt.core.types.BasicType;
import jolie.runtime.Value;

public class CommandExecutionRequest_workingDirectory extends BasicType<String> {

    public CommandExecutionRequest_workingDirectory(String root) {
        super(root);
    }

    public static CommandExecutionRequest_workingDirectory parse(Value value) {
        if (value != null && value.isString()) {
            return new CommandExecutionRequest_workingDirectory(value.strValue());
        } else {
            return null;
        }
    }

    public Value toValue() {
        Value value = super.toValue();
        return value;
    }
}
