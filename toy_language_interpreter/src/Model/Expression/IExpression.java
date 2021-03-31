package Model.Expression;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Type.IType;
import Model.Value.IValue;
import Repository.CustomException;

public interface IExpression {
    IValue evaluate(IDictionary<String, IValue> symbolsTable, IHeap<IValue> heapTable) throws CustomException;
    IType typeCheck(Dictionary<String, IType> typeEnv) throws CustomException;
}
