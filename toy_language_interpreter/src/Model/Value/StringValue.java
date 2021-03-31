package Model.Value;

import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.StringType;

public class StringValue implements IValue{
    String value;
    public StringValue(String s) { this.value = s; }
    public String getValue() { return this.value; }
    public String toString() {
        return this.value;
    }
    public IType getType() { return new StringType(); }
    public boolean equals(Object other) {
        return (other instanceof StringValue);
    }
}
