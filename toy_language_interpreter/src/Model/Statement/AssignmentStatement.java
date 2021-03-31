package Model.Statement;
import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Value.IValue;
import Repository.CustomException;

public class AssignmentStatement implements IStatement {
    String id;
    IExpression expression;

    public AssignmentStatement(String id, IExpression exp) {
        this.id = id;
        this.expression = exp;
    }
    public String toString() { return this.id + "=" + this.expression.toString(); }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IHeap<IValue> heapTable = state.getHeapTable();

        if (symbolsTable.isDefined(this.id)) {
            IValue value = this.expression.evaluate(symbolsTable, heapTable);
            IType typeId = symbolsTable.lookup(this.id).getType();
            if(value.getType().equals(typeId)) symbolsTable.update(this.id, value);
            else throw new CustomException("The declared type of variable "+ this.id +" and the type of the assigned expression do not match!\n");
        }
        else throw new CustomException("The used variable " + this.id + " was not declared before!\n");
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType variableType = typeEnv.lookup(this.id);
        IType expressionType = this.expression.typeCheck(typeEnv);
        if(variableType.equals(expressionType)) return typeEnv;
        else throw new CustomException("Assignment statement: right hand side and left hand side have different types!");
    }
}
