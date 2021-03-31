package Model.ADT;

import Repository.CustomException;

public interface IStack<T> {

    T pop() throws CustomException;
    void push(T value);
    boolean isEmpty();
    String toString();
}
