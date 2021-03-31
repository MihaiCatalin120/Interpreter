package Model.Type;

import Model.Value.IValue;
import Model.Value.IntValue;

public class IntType implements IType {
    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
    @Override
    public boolean equals(Object other) {
        return (other instanceof IntType);
    }
    @Override
    public String toString() { return "int"; }
}
