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

public class HeapAllocationStatement implements IStatement {
    String variableName;
    IExpression expression;

    public HeapAllocationStatement(String s, IExpression e) {
        this.variableName = s;
        this.expression = e;
    }

    @Override
    public ProgramState execute(ProgramState state) throws CustomException {
        IDictionary<String, IValue> symbolsTable = state.getSymbolsTable();
        IHeap<IValue> heapTable = state.getHeapTable();

        if(symbolsTable.isDefined(this.variableName)) {
            IValue variableValue = symbolsTable.lookup(this.variableName);
            if(variableValue.getType() instanceof ReferenceType) {
                ReferenceValue rVariableValue = (ReferenceValue) variableValue;
                ReferenceType rVariableType = (ReferenceType) rVariableValue.getType();
                boolean exists = rVariableValue.getAddress() != 0 && heapTable.isDefined(rVariableValue.getAddress());
                IValue value = this.expression.evaluate(symbolsTable, heapTable);
                if(rVariableType.getInner().equals(value.getType())) {
                    int newAddress = heapTable.getFreeLocation();
                    heapTable.add(value);
                    symbolsTable.update(this.variableName, new ReferenceValue(newAddress, rVariableType.getInner()));
                    if(exists) {
                        for(int currentAddress : heapTable.getContent().keySet()) {
                            IValue val = heapTable.lookup(currentAddress);
                            if(val instanceof ReferenceValue) {
                                ReferenceValue referenceValue = (ReferenceValue)val;
                                if (referenceValue.getAddress() == rVariableValue.getAddress()) {
                                    heapTable.update(currentAddress, new ReferenceValue(newAddress, referenceValue.getInnerType()));
                                }
                            }
                        }
                    }
                }
                else throw new CustomException("The given expression does not evaluate to the given type!\n");
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
        else throw new CustomException("New statement: right hand side and left hand side have different types!");
    }

    @Override
    public String toString() {
        return "New(" + this.variableName + "," + this.expression.toString() + ")";
    }
}