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

public class ConditionalStatement implements IStatement {
    IExpression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public ConditionalStatement(IExpression expression, IStatement thenS, IStatement elseS) {
        this.expression = expression;
        this.thenStatement = thenS;
        this.elseStatement = elseS;
    }
    public String toString() { return "If "+ this.expression.toString()+", then " + this.thenStatement.toString() +", else "+this.elseStatement.toString();}

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IStack<IStatement> stack = state.getExecutionStack();
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IHeap<IValue> heapTable = state.getHeapTable();

        IValue condition = this.expression.evaluate(symbolsTable, heapTable);
        if (condition.getType().equals(new BoolType())) {
            BoolValue bool_condition = (BoolValue) condition;
            if(bool_condition.getValue()) {
                stack.push(this.thenStatement);
            }
            else stack.push(this.elseStatement);
        }
        else throw new CustomException("Conditional expression is not a boolean!\n");
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType expressionType = this.expression.typeCheck(typeEnv);
        if(expressionType.equals(new BoolType())) {
            this.thenStatement.typeCheck( (Dictionary<String, IType>) typeEnv.copy());
            this.elseStatement.typeCheck( (Dictionary<String, IType>) typeEnv.copy());
            return typeEnv;
        }
        else throw new CustomException("Conditional statement: the conditional expression is not of type boolean!");
    }
}
