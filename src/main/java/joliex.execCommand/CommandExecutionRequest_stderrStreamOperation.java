package joliex.execCommand;

import jsdt.core.types.BasicType;
import jolie.runtime.Value;

public class CommandExecutionRequest_stderrStreamOperation extends BasicType<String> {

    public CommandExecutionRequest_stderrStreamOperation(String root) {
        super(root);
    }

    public static CommandExecutionRequest_stderrStreamOperation parse(Value value) {
        if (value != null && value.isString()) {
            return new CommandExecutionRequest_stderrStreamOperation(value.strValue());
        } else {
            return null;
        }
    }

    public Value toValue() {
        Value value = super.toValue();
        return value;
    }
}
