package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.ILock;
import Model.ADT.IStack;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Repository.CustomException;

public class UnlockStatement implements IStatement {
    String variableName;

    public UnlockStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        ILock<Integer> lockTable = state.getLockTable();
        IStack<IStatement> stack = state.getExecutionStack();

        IValue foundIndex = symbolsTable.lookup(variableName);
        if(!foundIndex.getType().equals(new IntType())) throw new CustomException(variableName + "has not been declared as an integer!\n");
        IntValue indexValue = (IntValue) foundIndex;

        if(!lockTable.isDefined(indexValue.getValue())) throw new CustomException("Index is not an entry in the lock table!\n");

        if(lockTable.lookup(indexValue.getValue()) == state.getId()) lockTable.update(indexValue.getValue(), -1);

        return null;
    }

    @Override
    public String toString() {
        return "unlock(" + variableName + ")";
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType variableType = typeEnv.lookup(variableName);
        if(variableType.equals(new IntType())) return typeEnv;
        else throw new CustomException("New Lock statement: " + variableName + " is not declared as an integer!\n");
    }
}
