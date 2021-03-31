package View;

import Controller.Controller;
import Repository.CustomException;

public class RunExampleCommand extends Command{
    private final Controller controller;
    public RunExampleCommand(String key, String description, Controller c) {
        super(key, description);
        this.controller = c;
    }

    @Override
    public void execute() {
        try {
            this.controller.allSteps();
        } catch (CustomException e) {
            System.out.println("Exception thrown : " + e);
        }
    }
}
