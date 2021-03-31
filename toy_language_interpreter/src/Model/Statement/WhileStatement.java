package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ADT.IStack;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Repository.CustomException;

public class WhileStatement implements IStatement {
    IExpression expression;
    IStatement statement;

    public WhileStatement(IExpression e, IStatement s) {
        this.expression = e;
        this.statement = s;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IStack<IStatement> stack = state.getExecutionStack();
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IHeap<IValue> heapTable = state.getHeapTable();

        IValue condition = this.expression.evaluate(symbolsTable, heapTable);
        if (condition.getType().equals(new BoolType())) {
            BoolValue bool_condition = (BoolValue) condition;
            if(bool_condition.getValue()) {
                stack.push(new WhileStatement(this.expression, this.statement));
                stack.push(this.statement);
            }
        }
        else throw new CustomException("Conditional expression is not a boolean!\n");
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType expressionType = this.expression.typeCheck(typeEnv);
        if(expressionType.equals(new BoolType())) {
            this.statement.typeCheck( (Dictionary<String, IType>) typeEnv.copy());
            return typeEnv;
        }
        else throw new CustomException("While statement: the conditional expression is not of type boolean!");
    }

    @Override
    public String toString() {
        return "(While " + this.expression.toString() + ", " + this.statement.toString() + ")";
    }
}
