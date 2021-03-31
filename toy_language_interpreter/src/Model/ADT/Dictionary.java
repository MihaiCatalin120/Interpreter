package Model.ADT;

import Repository.CustomException;

import java.util.Collection;
import java.util.HashMap;

public class Dictionary<T1,T2> implements IDictionary<T1,T2> {
    HashMap<T1, T2> dictionary;
    public Dictionary() {
        this.dictionary = new HashMap<T1, T2>();
    }

    @Override
    public void add(T1 id, T2 value) {
        this.dictionary.put(id, value);
    }

    @Override
    public void update(T1 id, T2 new_value) {
        this.dictionary.replace(id, new_value);
    }

    @Override
    public T2 lookup(T1 id) {

        try {
            return this.dictionary.get(id);
        } catch (Exception e) {
            throw new CustomException("Id non-existent in dictionary!\n");
        }
    }

    @Override
    public boolean isDefined(T1 id) {
        return this.dictionary.get(id) != null;
    }

    @Override
    public T2 remove(T1 id) {
        return this.dictionary.remove(id);
    }

    @Override
    public Collection<T2> getContent() {
        return this.dictionary.values();
    }

    @Override
    public IDictionary<T1, T2> copy() {
        IDictionary<T1, T2> dictionaryCopy = new Dictionary<>();
        this.dictionary.forEach(dictionaryCopy::add);
        return dictionaryCopy;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        this.dictionary.forEach((key, value) -> result.append(key.toString()).append(" --> ").append(value.toString()).append("\n"));
        return result.toString();
    }

    @Override
    public String idsToString() {
        StringBuilder result = new StringBuilder();
        this.dictionary.forEach((key, value) -> result.append(key.toString()).append("\n"));
        return result.toString();
    }
}
