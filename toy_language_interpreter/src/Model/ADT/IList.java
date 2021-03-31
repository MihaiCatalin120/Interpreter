package Model.ADT;

import Repository.CustomException;

public interface IList<T> {
    void add(T v);
    T pop() throws CustomException;
    String toString();
}
