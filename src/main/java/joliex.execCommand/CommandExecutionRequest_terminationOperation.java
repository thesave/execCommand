package joliex.execCommand;

import jsdt.core.types.BasicType;
import jolie.runtime.Value;

public class CommandExecutionRequest_terminationOperation extends BasicType<String> {

    public CommandExecutionRequest_terminationOperation(String root) {
        super(root);
    }

    public static CommandExecutionRequest_terminationOperation parse(Value value) {
        if (value != null && value.isString()) {
            return new CommandExecutionRequest_terminationOperation(value.strValue());
        } else {
            return null;
        }
    }

    public Value toValue() {
        Value value = super.toValue();
        return value;
    }
}
