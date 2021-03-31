package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;

public class IntValue implements IValue {
    int value;

    public IntValue(int v) { this.value = v; }

    public int getValue() { return this.value; }
    public String toString() { return Integer.toString(this.value); }
    public IType getType() { return new IntType(); }
    public boolean equals(Object other) {
        return (other instanceof IntValue);
    }
}
