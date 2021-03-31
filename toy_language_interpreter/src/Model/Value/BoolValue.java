package Model.Value;

import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;

public class BoolValue implements IValue {
    boolean value;
    public BoolValue(boolean b) { this.value = b; }
    public boolean getValue() { return this.value; }
    public String toString() {
        if(this.value) return "True";
        return "False";
    }
    public IType getType() { return new BoolType(); }
    public boolean equals(Object other) {
        return (other instanceof BoolValue);
    }
}
