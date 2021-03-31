package Model.Expression;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Type.IType;
import Model.Type.ReferenceType;
import Model.Value.IValue;
import Model.Value.ReferenceValue;
import Repository.CustomException;

public class HeapReadingExpression implements IExpression {
    IExpression expression;

    public HeapReadingExpression(IExpression e) { this.expression = e; }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolsTable, IHeap<IValue> heapTable) throws CustomException {
        IValue value = this.expression.evaluate(symbolsTable, heapTable);
        if(value instanceof ReferenceValue) {
            ReferenceValue referenceValue = (ReferenceValue) value;
            int address = referenceValue.getAddress();
            return heapTable.lookup(address);
        }
        else throw new CustomException("Expression is not evaluated to a reference!\n");
    }

    @Override
    public IType typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType type = this.expression.typeCheck(typeEnv);
        if(type instanceof ReferenceType) {
            ReferenceType refType = (ReferenceType) type;
            return refType.getInner();
        }
        else throw new CustomException("Heap reading expression: the heap reading argument is not a reference type!");
    }

    @Override
    public String toString() {
        return "ReadHeap(" + this.expression.toString() + ")";
    }
}