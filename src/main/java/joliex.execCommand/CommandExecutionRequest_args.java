package joliex.execCommand;

import jsdt.core.types.BasicType;
import jolie.runtime.Value;

public class CommandExecutionRequest_args extends BasicType<String> {

    public CommandExecutionRequest_args(String root) {
        super(root);
    }

    public static CommandExecutionRequest_args parse(Value value) {
        if (value != null && value.isString()) {
            return new CommandExecutionRequest_args(value.strValue());
        } else {
            return null;
        }
    }

    public Value toValue() {
        Value value = super.toValue();
        return value;
    }
}
