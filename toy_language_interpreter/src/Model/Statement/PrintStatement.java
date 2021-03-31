package Model.Statement;
import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ADT.IList;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Value.IValue;
import Repository.CustomException;

public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression exp) {
        this.expression = exp;
    }
    public String toString() { return "print(" + this.expression.toString() + ")"; }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IList<IValue> output = state.getOutput();
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IHeap<IValue> heapTable = state.getHeapTable();
        output.add(this.expression.evaluate(symbolsTable, heapTable));
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        this.expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
