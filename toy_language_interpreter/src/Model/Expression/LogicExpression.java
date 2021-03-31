package Model.Expression;

import Model.ADT.Dictionary;
import Model.ADT.IDictionary;
import Model.ADT.IHeap;
import Model.Type.BoolType;
import Model.Type.IType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Repository.CustomException;

public class LogicExpression implements IExpression{
    IExpression first_expression;
    IExpression second_expression;
    String operand;

    LogicExpression(String op, IExpression f_exp, IExpression s_exp) {
        this.first_expression = f_exp;
        this.second_expression = s_exp;
        this.operand = op;
    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> symbolsTable, IHeap<IValue> heapTable) throws CustomException {
        IValue first_value, second_value;
        first_value = this.first_expression.evaluate(symbolsTable, heapTable);
        if(first_value.getType().equals(new BoolType())) {
            second_value = this.second_expression.evaluate(symbolsTable, heapTable);
            if(second_value.getType().equals(new BoolType())) {
                BoolValue first_bool = (BoolValue) first_value;
                BoolValue second_bool = (BoolValue) second_value;
                boolean left_side = first_bool.getValue();
                boolean right_side = second_bool.getValue();
                switch(this.operand) {
                    case "and" -> { return new BoolValue(left_side && right_side); }
                    case "or" -> { return new BoolValue(left_side || right_side); }
                    default -> { throw new CustomException("Invalid operand!\n"); }
                }
            }
            else throw new CustomException("Second operand is not an boolean!\n");
        }
        else throw new CustomException("First operand is not an boolean!\n");
    }

    @Override
    public IType typeCheck(Dictionary<String, IType> typeEnv) throws CustomException {
        IType first_type, second_type;
        first_type = this.first_expression.typeCheck(typeEnv);
        second_type = this.second_expression.typeCheck(typeEnv);
        if(first_type.equals(new BoolType())) {
            if(second_type.equals(new BoolType())) return new BoolType();
            else throw new CustomException("Logic expression: second operand is not an integer!");
        }
        else throw new CustomException("Logic expression: first operand is not an integer!");
    }

    @Override
    public String toString() {
        return this.first_expression.toString() + " " + this.operand + " " + this.second_expression;
    }
}
