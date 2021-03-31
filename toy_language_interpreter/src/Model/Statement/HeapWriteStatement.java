package Model.Statement;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IType;
import Model.Type.ReferenceType;
import Model.Value.IValue;
import Model.Value.ReferenceValue;
import Repository.CustomException;

public class HeapWriteStatement implements IStatement {
    String variableName;
    IExpression expression;

    public HeapWriteStatement(String s, IExpression e) {
        this.variableName = s;
        this.expression = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IHeap<IValue> heapTable = state.getHeapTable();

        if(symbolsTable.isDefined(this.variableName)) {
            IValue variableValue = symbolsTable.lookup(this.variableName);
            IType variableType = variableValue.getType();
            if(variableType instanceof ReferenceType) {
                ReferenceValue rVariableValue = (ReferenceValue) variableValue;
                IValue value = this.expression.evaluate(symbolsTable, heapTable);
                if(heapTable.isDefined(rVariableValue.getAddress())) {
                    if(value.getType().equals(rVariableValue.getInnerType())) {
                        heapTable.update(rVariableValue.getAddress(), value);
                    }
                    else throw new CustomException("The reference type is not the same as the evaluated type!\n");
                }
                else throw new CustomException("The reference address is not a key in the heap!\n");
            }
            else throw new CustomException(this.variableName + " is not a reference!\n");
        }
        else throw new CustomException("Given variable is not yet defined!\n");
        return null;
    }

    @Override
    public Dictionary<String, IType> typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType variableType = typeEnv.lookup(this.variableName);
        IType expressionType = this.expression.typeCheck(typeEnv);
        if(variableType.equals(new ReferenceType(expressionType))) return typeEnv;
        else throw new CustomException("Heap write statement: right hand side and left hand side have different types!");
    }

    @Override
    public String toString() {
        return "WriteHeap(" + this.variableName.toString() + "," + this.expression.toString() + ")";
    }
}
