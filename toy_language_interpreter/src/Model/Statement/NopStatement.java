package Model.Statement;

import Model.ADT.Dictionary;
import Model.ProgramState;
import Model.Type.IType;
import Repository.CustomException;

public class NopStatement implements IStatement{

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        return typeEnv;
    }
}
