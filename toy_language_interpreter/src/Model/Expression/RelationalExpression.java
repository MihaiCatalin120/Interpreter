package Model.Expression;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;
import Repository.CustomException;

public class RelationalExpression implements IExpression{
    IExpression first_expression;
    IExpression second_expression;
    String operand;

    public RelationalExpression(String op, IExpression f_exp, IExpression s_exp) {
        this.first_expression = f_exp;
        this.second_expression = s_exp;
        this.operand = op;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolsTable, IHeap<IValue> heapTable) throws CustomException {
        IValue first_value, second_value;
        first_value = this.first_expression.evaluate(symbolsTable, heapTable);
        if(first_value.getType().equals(new IntType())) {
            second_value = this.second_expression.evaluate(symbolsTable, heapTable);
            if(second_value.getType().equals(new IntType())) {
                IntValue first_int = (IntValue) first_value;
                IntValue second_int = (IntValue) second_value;
                int first_number = first_int.getValue();
                int second_number = second_int.getValue();
                switch(this.operand) {
                    case "<" -> { return new BoolValue(first_number < second_number); }
                    case "<=" -> { return new BoolValue(first_number <= second_number); }
                    case "==" -> { return new BoolValue(first_number == second_number); }
                    case "!=" -> { return new BoolValue(first_number != second_number); }
                    case ">" -> { return new BoolValue(first_number > second_number); }
                    case ">=" -> { return new BoolValue(first_number >= second_number); }
                    default -> { throw new CustomException("Invalid operand!\n"); }
                }
            }
            else throw new CustomException("Second operand is not an integer!\n");
        }
        else throw new CustomException("First operand is not an integer!\n");
    }

    @Override
    public IType typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType first_type, second_type;
        first_type = this.first_expression.typeCheck(typeEnv);
        second_type = this.second_expression.typeCheck(typeEnv);
        if(first_type.equals(new IntType())) {
            if(second_type.equals(new IntType())) return new BoolType();
            else throw new CustomException("Relational expression: second operand is not an integer!");
        }
        else throw new CustomException("Relational expression: first operand is not an integer!");
    }

    @Override
    public String toString() {
        return this.first_expression.toString() + this.operand + this.second_expression.toString();
    }
}
