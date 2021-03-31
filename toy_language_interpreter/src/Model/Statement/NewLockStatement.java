package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.ILock;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Repository.CustomException;

public class NewLockStatement implements IStatement {
    String variableName;

    public NewLockStatement(String variable_name) {
        this.variableName = variable_name;
    }

    @Override
    public synchronized ProgramState execute(ProgramState state) throws CustomException {
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        ILock<Integer> lockTable = state.getLockTable();

        IValue variable_value = symbolsTable.lookup(variableName);

        if(!variable_value.getType().equals(new IntType())) {
            throw new CustomException(variableName + " is not declared integer!\n");
        }
        int free_location = lockTable.getFreeLocation();
        symbolsTable.update(variableName, new IntValue(free_location));
        lockTable.add(-1);
        return null;
    }

    @Override
    public String toString() {
        return "newLock(" + variableName + ")";
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType variableType = typeEnv.lookup(variableName);
        if(variableType.equals(new IntType())) return typeEnv;
        else throw new CustomException("New Lock statement: " + variableName + " is not declared as an integer!\n");
    }
}
