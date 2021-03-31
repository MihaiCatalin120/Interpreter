package Model.Type;

import Model.Value.BoolValue;
import Model.Value.IValue;

public class BoolType implements IType {
    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }
    @Override
    public boolean equals(Object other) {
        return (other instanceof BoolType);
    }
    @Override
    public String toString() { return "boolean"; }
}
