package Repository;


import Model.ProgramState;

import java.util.List;

public interface IRepository {
    void add(ProgramState element);
    ProgramState getCurrentProgram();
    void logProgramStateExecution(ProgramState state) throws CustomException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programList);
}
