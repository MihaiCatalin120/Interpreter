package Model.Value;

import Model.Type.IType;
import Model.Type.ReferenceType;

public class ReferenceValue implements IValue{
    int address;
    IType innerType;

    public ReferenceValue(int a, IType type) {
        this.address = a;
        this.innerType = type;
    }

    public int getAddress() { return this.address; }

    public void setAddress(int address) {
        this.address = address;
    }

    public IType getInnerType() {
        return this.innerType;
    }

    @Override
    public IType getType() { return new ReferenceType(innerType); }

    @Override
    public String toString() {
        return "(" + this.address + "," + this.innerType.toString() + ")";
    }
}
