package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.ADT.IStack;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.StringValue;
import Repository.CustomException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement{
    IExpression expression;

    public OpenReadFileStatement(IExpression exp) {
        this.expression = exp;
    }

    public String toString() { return "OpenReadFile(" + this.expression.toString() + ")"; }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<IValue> heapTable = state.getHeapTable();

        IValue value = this.expression.evaluate(symbolsTable, heapTable);
        if(value.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) value;
            String filePath = stringValue.getValue();
            if(fileTable.isDefined(filePath)) throw new CustomException("The file with given path is already open!\n");
            else {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(filePath));
                    fileTable.add(filePath, reader);
                } catch (FileNotFoundException e) {
                    throw new CustomException("File not found!");
                }
            }
        }
        else throw new CustomException("File name must be a string value!\n");
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType expressionType = this.expression.typeCheck(typeEnv);
        if(expressionType.equals(new StringType())) return typeEnv;
        else throw new CustomException("Open read file statement: file name must be a string value!");
    }
}
