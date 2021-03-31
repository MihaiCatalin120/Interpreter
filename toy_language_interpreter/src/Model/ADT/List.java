package Model.ADT;

import Repository.CustomException;

import java.util.LinkedList;

public class List<T> implements IList<T> {
    LinkedList<T> list;

    public List () {
        this.list = new LinkedList<>();
    }

    @Override
    public void add(T value) {
        this.list.add(value);
    }

    @Override
    public T pop() {
        if(this.list.isEmpty()) throw new CustomException("No elements left in list!\n");
        return this.list.remove();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (T element: this.list) result.append(element.toString()).append("\n");
        return result.toString();
    }
}
