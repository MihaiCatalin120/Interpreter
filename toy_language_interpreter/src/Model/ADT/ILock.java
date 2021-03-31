package Model.ADT;

import Repository.CustomException;

public interface ILock<T> {
    void add(T value);

    void update(int key, T value);

    int getFreeLocation();

    boolean isDefined(int address);

    T lookup(Integer address) throws CustomException;
    String toString();
}
