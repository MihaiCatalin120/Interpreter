package Controller;

import Model.ADT.IHeap;
import Model.ProgramState;
import Model.Type.ReferenceType;
import Model.Value.IValue;
import Model.Value.ReferenceValue;
import Repository.Repository;
import Repository.CustomException;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    public Repository repository;
    ExecutorService executor;

    public Controller(Repository r) {
        this.repository = r;
    }

    public void add(ProgramState state) {
        this.repository.add(state);
    }

    public void oneStepForAllPrograms(List<ProgramState> programStateList) throws CustomException {
        programStateList.forEach(program -> this.repository.logProgramStateExecution(program));

        List<Callable<ProgramState>> callables =
                programStateList.stream()
                    .map((ProgramState p) -> (Callable<ProgramState>)(p::step))
                    .collect(Collectors.toList());

        try {
            List<ProgramState> newProgramStateList =
                    this.executor.invokeAll(callables).stream()
                            .map(future -> {
                                try {
                                    return future.get();
                                } catch (InterruptedException e) {
                                    throw new CustomException("Process interrupted" + e.getMessage());
                                } catch (ExecutionException e) {
                                    throw new CustomException("Exception in process" + e.getMessage());
                                }
                            })
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());

            programStateList.addAll(newProgramStateList);

            programStateList.forEach(program -> this.repository.logProgramStateExecution(program));

            this.repository.setProgramList(programStateList);
        } catch (InterruptedException e) {
            throw new CustomException("Process interrupted" + e.getMessage());
        }
    }

    public void allSteps() {
        this.executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStateList = removeCompletedPrograms(this.repository.getProgramList());
        while(programStateList.size() > 0) {
            Map<Integer, IValue> newHeap = garbageCollector(mergeSymbolTables(programStateList), programStateList.get(0).getHeapTable().getContent());
            programStateList.forEach(program -> program.getHeapTable().setContent(newHeap));
            oneStepForAllPrograms(programStateList);
            programStateList = removeCompletedPrograms(this.repository.getProgramList());
        }
        this.executor.shutdownNow();
        this.repository.setProgramList(programStateList);
    }

    Map<Integer, IValue> garbageCollector(List<Integer> symbolsTableAddresses, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> symbolsTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getRecursiveAddresses(ReferenceValue value) {
        IHeap<IValue> heap = repository.getProgramList().get(0).getHeapTable();
        List<Integer> addresses = new LinkedList<>();
        addresses.add(value.getAddress());
        if (value.getInnerType() instanceof ReferenceType) {
            IValue val = heap.lookup(value.getAddress());
            while (val instanceof ReferenceValue) {
                ReferenceValue current = (ReferenceValue)val;
                addresses.add(current.getAddress());
                val = heap.lookup(current.getAddress());
            }
        }
        return addresses;
    }

    List<Integer> getAddresses(Collection<IValue> symbolTableValues) {
        return symbolTableValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v -> getRecursiveAddresses((ReferenceValue) v))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    List<Integer> mergeSymbolTables(List<ProgramState> programStateList) {
        return programStateList.stream()
                .map(program -> getAddresses(program.getSymbolsTable().getContent()))
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    List<ProgramState> removeCompletedPrograms(List<ProgramState> programStateList) {
        return programStateList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());

    }
}
