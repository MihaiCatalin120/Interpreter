package Model.Type;

import Model.Value.StringValue;
import Model.Value.IValue;

public class StringType implements IType{
    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }
    @Override
    public boolean equals(Object other) {
        return (other instanceof StringType);
    }
    @Override
    public String toString() { return "string"; }
}
