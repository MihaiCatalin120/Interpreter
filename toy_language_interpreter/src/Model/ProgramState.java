package Model;

import Model.ADT.*;
import Model.Statement.IStatement;
import Model.Value.IValue;
import Repository.CustomException;

import java.io.BufferedReader;

public class ProgramState {
    IStack<IStatement> executionStack;
    IDictionary<String, IValue> symbolsTable;
    IList<IValue> output;
    IDictionary<String, BufferedReader> fileTable;
    IHeap<IValue> heapTable;
    ILock<Integer> lockTable;
    IStatement originalProgram;
    int id;
    static int uniqueId = 1;

    public ProgramState(IStack<IStatement> stack, IDictionary<String, IValue> symbolsTable, IList<IValue> output, IDictionary<String, BufferedReader> fileTable, IHeap<IValue> heapTable, ILock<Integer> lockTable, IStatement program) {
        this.executionStack = stack;
        this.symbolsTable = symbolsTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heapTable = heapTable;
        this.lockTable = lockTable;
        this.originalProgram = program;
        this.id = uniqueId;
        uniqueId++;
        this.executionStack.push(program);
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(IStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public IDictionary<String, IValue> getSymbolsTable() {
        return symbolsTable;
    }

    public void setSymbolsTable(IDictionary<String, IValue> symbolsTable) {
        this.symbolsTable = symbolsTable;
    }

    public IList<IValue> getOutput() {
        return output;
    }

    public void setOutput(IList<IValue> output) {
        this.output = output;
    }

    public IDictionary<String, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public void setFileTable(IDictionary<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public IHeap<IValue> getHeapTable() {
        return this.heapTable;
    }

    public void setHeapTable(IHeap<IValue> heapTable) { this.heapTable = heapTable; }

    public ILock<Integer> getLockTable() { return this.lockTable;}

    public int getId() { return this.id;}

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public boolean isNotCompleted() { return !this.executionStack.isEmpty(); }

    public ProgramState step() throws CustomException {

        if(this.executionStack.isEmpty()) throw new CustomException("Program state stack is empty!\n");
        IStatement currentStatement = this.executionStack.pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString() {
        String result;
        result = "Current program state with id " + this.id + " :\n" +
                "Execution Stack:\n" + this.executionStack.toString() +
                "Symbols Table:\n" + this.symbolsTable.toString() +
                "Output List:\n" + this.output.toString() +
                "File Table:\n" + this.fileTable.idsToString() +
                "Heap Table:\n" + this.heapTable.toString() +
                "Lock Table:\n" + this.lockTable.toString() + "\n";
        return result;
    }


}
