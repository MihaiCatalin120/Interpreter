package Model.ADT;

import Repository.CustomException;

import java.util.HashMap;
import java.util.Map;


public class Heap<T> implements IHeap<T> {
    Map<Integer, T> heapTable;
    int freeLocation = 1;

    public Heap() { this.heapTable = new HashMap<Integer, T>(); }

    @Override
    public void add(T value) {
        this.heapTable.put(this.freeLocation++, value);
    }

    @Override
    public void update(int key, T value) {
        this.heapTable.put(key, value);
    }

    @Override
    public T lookup(Integer address) throws CustomException {
        try {
            return this.heapTable.get(address);
        }
        catch(Exception e) {
            throw new CustomException("No existing heap entry with this address!\n");
        }
    }

    @Override
    public int getFreeLocation() {
        return this.freeLocation;
    }

    @Override
    public boolean isDefined(int address) { return this.heapTable.get(address) != null; }

    @Override
    public Map<Integer, T> getContent() {
        return this.heapTable;
    }

    @Override
    public void setContent(Map<Integer, T> newContent) {
        this.heapTable = newContent;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        this.heapTable.forEach((key, value) -> result.append(key.toString()).append(" --> ").append(value.toString()).append("\n"));
        return result.toString();
    }
}

