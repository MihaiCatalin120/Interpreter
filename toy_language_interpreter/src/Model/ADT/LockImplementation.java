package Model.ADT;

import Repository.CustomException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockImplementation<T> implements ILock<T>{
    Map<Integer, T> lockTable;
    int freeLocation = 1;

    public LockImplementation() { this.lockTable = new HashMap<Integer, T>(); }

    @Override
    public void add(T value) {
        this.lockTable.put(this.freeLocation++, value);
    }

    @Override
    public void update(int key, T value) {
        Lock l = new ReentrantLock();
        l.lock();
        try {
            this.lockTable.put(key, value);
        } finally {
            l.unlock();
        }


    }

    @Override
    public T lookup(Integer address) throws CustomException {
        try {
            Lock l = new ReentrantLock();
            l.lock();
            try {
                return this.lockTable.get(address);
            } finally {
                l.unlock();
            }
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
    public boolean isDefined(int address) { return this.lockTable.get(address) != null; }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        this.lockTable.forEach((key, value) -> result.append(key.toString()).append(" --> ").append(value.toString()).append("\n"));
        return result.toString();
    }
}
