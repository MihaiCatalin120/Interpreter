package Model.ADT;

import Repository.CustomException;

import java.util.Collection;

public interface IDictionary<T1,T2> {

    void add(T1 id, T2 value);
    void update(T1 id, T2 new_value);
    T2 lookup(T1 id) throws CustomException;
    boolean isDefined(T1 id);
    T2 remove(T1 id);
    Collection<T2> getContent();
    IDictionary<T1,T2> copy();
    String toString();
    String idsToString();
}
