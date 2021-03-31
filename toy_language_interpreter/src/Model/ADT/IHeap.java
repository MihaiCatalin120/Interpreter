package Model.ADT;

import Repository.CustomException;

import java.util.Map;

public interface IHeap<T> {

    void add(T value);

    void update(int key, T value);

    T lookup(Integer address) throws CustomException;

    int getFreeLocation();

    boolean isDefined(int address);

    Map<Integer, T> getContent();

    void setContent(Map<Integer, T> newContent);

}
