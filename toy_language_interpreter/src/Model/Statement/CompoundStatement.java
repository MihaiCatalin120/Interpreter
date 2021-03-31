package Model.Statement;
import Model.ADT.Dictionary;
import Model.ADT.IStack;
import Model.ProgramState;
import Model.Type.IType;
import Repository.CustomException;

public class CompoundStatement implements IStatement {
    IStatement first;
    IStatement second;

    public CompoundStatement(IStatement f, IStatement s) {
        this.first = f;
        this.second = s;
    }
    public String toString() {
        return this.first.toString() + "; " + this.second.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(this.second);
        stack.push(this.first);
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        return this.second.typeCheck(this.first.typeCheck(typeEnv));
    }
}
