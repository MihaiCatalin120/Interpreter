package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IStack;
import Model.ProgramState;
import Model.Type.*;
import Model.Value.IValue;
import Repository.CustomException;

public class VariableDeclarationStatement implements IStatement {
    String name;
    IType type;

    public VariableDeclarationStatement(String n, IType t) {
        this.name = n;
        this.type = t;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();

        if (symbolsTable.isDefined(this.name)) {
            throw new CustomException("Variable with given name is already declared!\n");
        }
        else if(this.type.equals(new IntType())){
            symbolsTable.add(this.name, this.type.defaultValue());
        }
        else if(this.type.equals(new BoolType())) {
            symbolsTable.add(this.name, this.type.defaultValue());
        }
        else if(this.type.equals(new StringType())) {
            symbolsTable.add(this.name, this.type.defaultValue());
        }
        else if(this.type instanceof ReferenceType) {
            symbolsTable.add(this.name, this.type.defaultValue());
        }
        else throw new CustomException("Invalid value type!\n");
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        typeEnv.add(this.name, this.type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.name;
    }
}
