package Model.ADT;

import Repository.CustomException;
import java.util.Stack;

public class StackImplementation<T> implements IStack<T> {
    Stack<T> stack;

    public StackImplementation() {
        this.stack = new Stack<>();
    }
    @Override
    public T pop() {
        if(stack.isEmpty()) throw new CustomException("Stack has no elements!\n");
        return this.stack.pop();
    }

    @Override
    public void push(T value) {
        this.stack.push(value);
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        Stack<T> copy = new Stack<>();
        for (T element: this.stack) copy.push(element);
        StringBuilder result = new StringBuilder();
        while(!copy.isEmpty()) result.append(copy.pop().toString()).append("\n");
        return result.toString();
    }
}
