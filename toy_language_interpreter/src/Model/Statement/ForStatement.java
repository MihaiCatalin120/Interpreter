package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ADT.IStack;
import Model.Expression.IExpression;
import Model.Expression.RelationalExpression;
import Model.Expression.VariableNameExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.IValue;
import Repository.CustomException;

public class ForStatement implements IStatement {
    IExpression expression1;
    IExpression expression2;
    IExpression expression3;
    IStatement statement;

    public ForStatement(IExpression e1, IExpression e2, IExpression e3, IStatement s) {
        this.expression1 = e1;
        this.expression2 = e2;
        this.expression3 = e3;
        this.statement = s;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IStack<IStatement> stack = state.getExecutionStack();
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IHeap<IValue> heapTable = state.getHeapTable();

        if(!expression1.evaluate(symbolsTable, heapTable).getType().equals(new IntType())
        || !expression2.evaluate(symbolsTable, heapTable).getType().equals(new IntType())) {
            throw new CustomException("Expressions do not evaluate to an int type!\n");
        }
        else {
            IStatement new_statement =
                    new CompoundStatement(
                            new AssignmentStatement("v", expression1),
                            new WhileStatement(new RelationalExpression("<", new VariableNameExpression("v"), expression2)
                                    , new CompoundStatement(statement, new AssignmentStatement("v", expression3)))
                    );
            stack.push(new_statement);
            stack.push(new VariableDeclarationStatement("v", new IntType()));
            return null;
        }

    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType e1type = this.expression1.typeCheck(typeEnv);
        IType e2type = this.expression1.typeCheck(typeEnv);
        IType e3type = this.expression1.typeCheck(typeEnv);

        if(!e1type.equals(new IntType()) || !e2type.equals(new IntType()) || !e3type.equals(new IntType())) {
            throw new CustomException("For statement: the expressions are not of type int!\n");
        }
        else return typeEnv;
    }

    @Override
    public String toString() {
        return "for(v=" + expression1.toString() + ";v<" + expression2.toString() + ";v=" + expression3.toString() + ")" + statement.toString();
    }
}

