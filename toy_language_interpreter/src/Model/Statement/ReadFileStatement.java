package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.IValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.CustomException;

import java.io.BufferedReader;

public class ReadFileStatement implements IStatement{
    IExpression expression;
    String variableName;

    public ReadFileStatement(IExpression exp, String s) {
        this.expression = exp;
        this.variableName = s;
    }

    public String toString() { return "ReadFile(" + this.expression.toString() + "," + this.variableName + ")"; }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IDictionary<String, BufferedReader> fileTable = state.getFileTable();
        IHeap<IValue> heapTable = state.getHeapTable();

        if(symbolsTable.isDefined(this.variableName)) {
            IValue value = this.expression.evaluate(symbolsTable, heapTable);
            if(value.getType().equals(new StringType())) {
                StringValue stringValue = (StringValue) value;
                String filePath = stringValue.getValue();
                if(fileTable.isDefined(filePath)) {
                    IValue fileValue;
                    try {
                        BufferedReader reader = fileTable.lookup(filePath);
                        fileValue = new IntValue(Integer.parseInt(reader.readLine()));
                    } catch (Exception e) {
                        fileValue = new IntValue(0);
                    }
                    symbolsTable.update(this.variableName, fileValue);
                }
                else throw new CustomException("Given file is not yet open!\n");
            }
            else throw new CustomException("File name must be a string type!\n");
        }
        else throw new CustomException("Given variable is not yet defined!\n");
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType variableType = typeEnv.lookup(this.variableName);
        IType expressionType = this.expression.typeCheck(typeEnv);
        if(variableType.equals(new IntType())) {
            if(expressionType.equals(new StringType())) return typeEnv;
            else throw new CustomException("Read file statement: file name must be a string type!");
        }
        else throw new CustomException("Read file statement: expression was not evaluated to an int type!");
    }
}
