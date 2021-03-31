package Model.Expression;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Type.IType;
import Model.Value.IValue;
import Repository.CustomException;

public class ValueExpression implements IExpression {
    IValue value;

    public ValueExpression(IValue v) {
        this.value = v;
    }
    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolsTable, IHeap<IValue> heapTable) throws CustomException {
        return this.value;
    }

    @Override
    public IType typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        return this.value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
