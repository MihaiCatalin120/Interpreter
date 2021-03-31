package Model.Statement;

import Model.ADT.*;
import Model.ProgramState;
import Model.Type.IType;
import Model.Value.IValue;
import Repository.CustomException;

import java.io.BufferedReader;

public class ForkStatement implements IStatement {
    IStatement statement;

    public ForkStatement(IStatement s) { this.statement = s; }

    @Override
    public String toString() {
        return "fork(" + this.statement.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IList<IValue> output = state.getOutput();
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<IValue> heapTable = state.getHeapTable();
        ILock<Integer> lockTable = state.getLockTable();

        IDictionary<String, IValue> symbolsTableCopy = symbolsTable.copy();
        return new ProgramState(new StackImplementation<>(), symbolsTableCopy, output, fileTable, heapTable, lockTable, this.statement);
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        this.statement.typeCheck(typeEnv);
        return typeEnv;
    }
}
