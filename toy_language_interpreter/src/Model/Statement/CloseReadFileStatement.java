package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;
import Repository.CustomException;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStatement implements IStatement{
    IExpression expression;

    public CloseReadFileStatement(IExpression exp) { this.expression = exp; }

    public String toString() { return "CloseReadFile(" + this.expression.toString() + ")"; }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<IValue> heapTable = state.getHeapTable();

        IValue value = this.expression.evaluate(symbolsTable, heapTable);
        if(value.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) value;
            String filePath = stringValue.getValue();
            if(fileTable.isDefined(filePath)) {
                try {
                    BufferedReader reader = fileTable.lookup(filePath);
                    reader.close();
                    fileTable.remove(filePath);
                } catch (IOException e) {
                    throw new CustomException("Could not close file!");
                }

            }
            else throw new CustomException("The file with given path is not open!\n");
        }
        else throw new CustomException("File name must be a string value!\n");
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType expressionType = this.expression.typeCheck(typeEnv);
        if(expressionType.equals(new StringType())) return typeEnv;
        else throw new CustomException("Close read file statement: file name must be a string value!");
    }
}
