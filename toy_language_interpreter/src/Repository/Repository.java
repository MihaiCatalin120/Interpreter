package Repository;

import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Repository implements IRepository{
    ProgramState[] states;
    int numberOfStates;
    String filePath;
    boolean firstFileCall;

    public Repository(String path) {
        this.states = new ProgramState[100];
        this.numberOfStates = 0;
        this.filePath = path;
    }

    @Override
    public void add(ProgramState state) {
        this.states[numberOfStates] = state;
        this.numberOfStates++;
    }

    @Override
    public ProgramState getCurrentProgram() {
        if(this.numberOfStates == 0) throw new CustomException("No program states available!\n");
        return this.states[numberOfStates - 1];
    }

    @Override
    public void logProgramStateExecution(ProgramState state) throws CustomException {
        try {
            if (this.firstFileCall)
            {
                PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath, false)));
                logFile.write("");
                logFile.close();
                this.firstFileCall = false;
            }
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath, true)));
            System.out.println(state.toString());
            logFile.write(state.toString() + "\n");
            logFile.close();
        } catch(IOException e) {
            throw new CustomException("Invalid file path for repository!\n");
        }
    }

    @Override
    public List<ProgramState> getProgramList() {
        List<ProgramState> result = new LinkedList<>();
        for(int i = 0; i < this.numberOfStates; i++) {
            result.add(this.states[i]);
        }
        return result;
    }

    @Override
    public void setProgramList(List<ProgramState> programList) {
        this.states = new ProgramState[100];
        this.numberOfStates = programList.size();
        for(int i = 0; i < this.numberOfStates; i++) {
            this.states[i] = programList.get(i);
        }
    }
}
