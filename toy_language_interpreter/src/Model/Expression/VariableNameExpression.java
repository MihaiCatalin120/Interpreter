package Model.Expression;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Type.IType;
import Model.Value.IValue;
import Repository.CustomException;

public class VariableNameExpression implements IExpression{
    String id;

    public VariableNameExpression(String id) { this.id  = id; }
    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolsTable, IHeap<IValue> heapTable) throws CustomException { return symbolsTable.lookup(this.id); }

    @Override
    public IType typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        return typeEnv.lookup(this.id);
    }

    @Override
    public String toString() {
        return this.id;
    }
}
