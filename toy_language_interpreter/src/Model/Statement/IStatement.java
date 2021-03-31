package Model.Statement;
import Model.ADT.Dictionary;
import Model.ProgramState;
import Model.Type.IType;
import Repository.CustomException;
public interface IStatement {
    public ProgramState execute (ProgramState state) throws CustomException;
    Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException;
}
