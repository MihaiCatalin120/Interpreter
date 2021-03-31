package View;

import Controller.Controller;
import Model.ADT.*;
import Model.Expression.*;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.ReferenceType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.ReferenceValue;
import Model.Value.StringValue;
import Repository.Repository;
import Repository.CustomException;

public class Main {

    public static void main(String[] args) {
        IStatement statement1 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                new PrintStatement(new VariableNameExpression("v"))));
        try {
            statement1.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 1: " + e.getMessage() + "\n");
        }
        ProgramState state1 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement1);
        Repository repository1 = new Repository("log1.txt");
        repository1.add(state1);
        Controller controller1 = new Controller(repository1);

        IStatement statement2 =
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("b", new IntType()),
                                new CompoundStatement(
                                        new AssignmentStatement("a",
                                                new ArithmeticExpression("+",
                                                        new ValueExpression(new IntValue(2)),
                                                        new ArithmeticExpression("*",
                                                                new ValueExpression(new IntValue(3)),
                                                                new ValueExpression(new IntValue(5))))),
                                        new CompoundStatement(
                                                new AssignmentStatement("b",
                                                        new ArithmeticExpression("+",
                                                                new VariableNameExpression("a"),
                                                                new ValueExpression(new IntValue(1)))),
                                                new PrintStatement(new VariableNameExpression("b"))))));
        try {
            statement2.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 2: " + e.getMessage() + "\n");
        }
        ProgramState state2 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement2);
        Repository repository2 = new Repository("log2.txt");
        repository2.add(state2);
        Controller controller2 = new Controller(repository2);

        IStatement statement3 =
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new BoolType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(
                                        new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                        new CompoundStatement(
                                                new ConditionalStatement(
                                                        new VariableNameExpression("a"),
                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                                new PrintStatement(new VariableNameExpression("v"))))));
        try {
            statement3.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 3: " + e.getMessage() + "\n");
        }
        ProgramState state3 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement3);
        Repository repository3 = new Repository("log3.txt");
        repository3.add(state3);
        Controller controller3 = new Controller(repository3);

        IStatement statement4 =
                new CompoundStatement(
                        new VariableDeclarationStatement("varf", new StringType()),
                        new CompoundStatement(
                                new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                                new CompoundStatement(
                                        new OpenReadFileStatement(new VariableNameExpression("varf")),
                                        new CompoundStatement(
                                                new VariableDeclarationStatement("varc", new IntType()),
                                                new CompoundStatement(
                                                        new ReadFileStatement(new VariableNameExpression("varf"), "varc"),
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableNameExpression("varc")),
                                                                new CompoundStatement(
                                                                        new ReadFileStatement(new VariableNameExpression("varf"), "varc"),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableNameExpression("varc")),
                                                                                new CloseReadFileStatement(new VariableNameExpression("varf"))))))))));
        try {
            statement4.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 4: " + e.getMessage() + "\n");
        }
        ProgramState state4 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement4);
        Repository repository4 = new Repository("log4.txt");
        repository4.add(state4);
        Controller controller4 = new Controller(repository4);

        IStatement statement5 =
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(
                                        new AssignmentStatement("a", new ValueExpression(new IntValue(20))),
                                        new CompoundStatement(
                                                new ConditionalStatement(
                                                        new RelationalExpression( "<=", new VariableNameExpression("a"), new ValueExpression(new IntValue(10))),
                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                        new AssignmentStatement("v", new ValueExpression(new IntValue(5)))),
                                                new PrintStatement(new VariableNameExpression("v"))))));
        try {
            statement5.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 5: " + e.getMessage() + "\n");
        }
        ProgramState state5 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement5);
        Repository repository5 = new Repository("log5.txt");
        repository5.add(state5);
        Controller controller5 = new Controller(repository5);

        IStatement statement6 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                                new CompoundStatement(
                                        new WhileStatement(
                                                new RelationalExpression(">", new VariableNameExpression("v"), new ValueExpression(new IntValue(0))),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableNameExpression("v")),
                                                        new AssignmentStatement("v", new ArithmeticExpression("-", new VariableNameExpression("v"), new ValueExpression(new IntValue(1)))))),
                                        new PrintStatement(new VariableNameExpression("v")))));
        try {
            statement6.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 6: " + e.getMessage() + "\n");
        }
        ProgramState state6 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement6);
        Repository repository6 = new Repository("log6.txt");
        repository6.add(state6);
        Controller controller6 = new Controller(repository6);

        IStatement statement7 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("a", new VariableNameExpression("v")),
                                                new CompoundStatement(
                                                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                                                        new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableNameExpression("a")))))))));
        try {
            statement7.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 7: " + e.getMessage() + "\n");
        }
        ProgramState state7 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement7);
        Repository repository7 = new Repository("log7.txt");
        repository7.add(state7);
        Controller controller7 = new Controller(repository7);


        IStatement statement8 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("a", new VariableNameExpression("v")),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableNameExpression("v")),
                                                        new PrintStatement(new VariableNameExpression("a")))))));
        try {
            statement8.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 8: " + e.getMessage() + "\n");
        }
        ProgramState state8 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement8);
        Repository repository8 = new Repository("log8.txt");
        repository8.add(state8);
        Controller controller8 = new Controller(repository8);

        IStatement statement9 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("a", new VariableNameExpression("v")),
                                                new CompoundStatement(
                                                        new PrintStatement(new HeapReadingExpression(new VariableNameExpression("v"))),
                                                        new PrintStatement(new ArithmeticExpression("+",
                                                                new HeapReadingExpression(new HeapReadingExpression(new VariableNameExpression("a"))),
                                                                new ValueExpression(new IntValue(5)))))))));
        try {
            statement9.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 9: " + e.getMessage() + "\n");
        }
        ProgramState state9 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement9);
        Repository repository9 = new Repository("log9.txt");
        repository9.add(state9);
        Controller controller9 = new Controller(repository9);

        IStatement statement10 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new PrintStatement(new HeapReadingExpression(new VariableNameExpression("v"))),
                                        new CompoundStatement(
                                                new HeapWriteStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ArithmeticExpression("+",
                                                        new HeapReadingExpression(new VariableNameExpression("v")),
                                                        new ValueExpression(new IntValue(5))))))));
        try {
            statement10.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 10: " + e.getMessage() + "\n");
        }
        ProgramState state10 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement10);
        Repository repository10 = new Repository("log10.txt");
        repository10.add(state10);
        Controller controller10 = new Controller(repository10);

        IStatement statement11 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                                new CompoundStatement(
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("a", new ValueExpression(new IntValue(22))),
                                                new CompoundStatement(
                                                        new ForkStatement(
                                                                new CompoundStatement(
                                                                        new HeapWriteStatement("a", new ValueExpression(new IntValue(30))),
                                                                        new CompoundStatement(
                                                                                new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                                new CompoundStatement(
                                                                                        new PrintStatement(new VariableNameExpression("v")),
                                                                                        new PrintStatement(new HeapReadingExpression(new VariableNameExpression("a"))))))),
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableNameExpression("v")),
                                                                new PrintStatement(new HeapReadingExpression(new VariableNameExpression("a")))))))));
        try {
            statement11.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 11: " + e.getMessage() + "\n");
        }
        ProgramState state11 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement11);
        Repository repository11 = new Repository("log11.txt");
        repository11.add(state11);
        Controller controller11 = new Controller(repository11);

        IStatement statement12 =
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new HeapAllocationStatement("a", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new ForStatement(new ValueExpression(new BoolValue(true)), new ValueExpression(new IntValue(3))
                                                , new ArithmeticExpression("+", new VariableNameExpression("v"), new ValueExpression(new IntValue(1))),
                                                new ForkStatement(new CompoundStatement(new PrintStatement(new VariableNameExpression("v"))
                                                ,new AssignmentStatement("v", new ArithmeticExpression("*", new VariableNameExpression("v"), new HeapReadingExpression(new VariableNameExpression("a"))))))),
                                        new PrintStatement(new HeapReadingExpression(new VariableNameExpression("a"))))));
        try {
            statement12.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 12: " + e.getMessage() + "\n");
        }
        ProgramState state12 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement12);
        Repository repository12 = new Repository("log12.txt");
        repository12.add(state12);
        Controller controller12 = new Controller(repository12);

        IStatement statement13 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v1", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new VariableDeclarationStatement("v2", new ReferenceType(new IntType())),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("x", new BoolType()),
                                        new CompoundStatement(
                                                new VariableDeclarationStatement("q", new IntType()),
                                                new CompoundStatement(
                                                        new HeapAllocationStatement("v1", new ValueExpression(new IntValue(20))),
                                                        new CompoundStatement(
                                                                new HeapAllocationStatement("v2", new ValueExpression(new IntValue(30))),
                                                                new CompoundStatement(
                                                                        new NewLockStatement("x"),
                                                                        new CompoundStatement(
                                                                                new ForkStatement(
                                                                                        new CompoundStatement(
                                                                                                new ForkStatement(
                                                                                                        new CompoundStatement(
                                                                                                                new LockStatement("x"),
                                                                                                                new CompoundStatement(
                                                                                                                        new HeapWriteStatement("v1", new ArithmeticExpression("-", new HeapReadingExpression(new VariableNameExpression("v1")), new ValueExpression(new IntValue(1)))),
                                                                                                                        new UnlockStatement("x")
                                                                                                                )
                                                                                                        )
                                                                                                ), new CompoundStatement(
                                                                                                        new LockStatement("x"),
                                                                                                        new CompoundStatement(
                                                                                                                new HeapWriteStatement("v1", new ArithmeticExpression("*", new HeapReadingExpression(new VariableNameExpression("v1")), new ValueExpression(new IntValue(10)))),
                                                                                                                new UnlockStatement("x")
                                                                                                        )
                                                                                        )

                                                                                        )
                                                                                ), new CompoundStatement(
                                                                                        new NewLockStatement("q"),
                                                                                        new CompoundStatement(
                                                                                                new ForkStatement(
                                                                                                        new CompoundStatement(
                                                                                                                new ForkStatement(
                                                                                                                        new CompoundStatement(
                                                                                                                                new LockStatement("q"),
                                                                                                                                new CompoundStatement(
                                                                                                                                        new HeapWriteStatement("v2", new ArithmeticExpression("+", new HeapReadingExpression(new VariableNameExpression("v2")), new ValueExpression(new IntValue(5)))),
                                                                                                                                        new UnlockStatement("q")
                                                                                                                                )
                                                                                                                        )
                                                                                                                ),
                                                                                                                new CompoundStatement(
                                                                                                                        new LockStatement("q"),
                                                                                                                        new CompoundStatement(
                                                                                                                                new HeapWriteStatement("v2", new ArithmeticExpression("*", new HeapReadingExpression(new VariableNameExpression("v2")), new ValueExpression(new IntValue(5)))),
                                                                                                                                new UnlockStatement("q")
                                                                                                                        )
                                                                                                                )
                                                                                                        )
                                                                                                ),
                                                                                                new CompoundStatement(
                                                                                                        new NopStatement(),
                                                                                                        new CompoundStatement(
                                                                                                                new NopStatement(),
                                                                                                                new CompoundStatement(
                                                                                                                        new NopStatement(),
                                                                                                                        new CompoundStatement(
                                                                                                                                new NopStatement(),
                                                                                                                                new CompoundStatement(
                                                                                                                                        new LockStatement("x"),
                                                                                                                                        new CompoundStatement(
                                                                                                                                                new PrintStatement(new HeapReadingExpression(new VariableNameExpression("v1"))),
                                                                                                                                                new CompoundStatement(
                                                                                                                                                        new UnlockStatement("x"),
                                                                                                                                                        new CompoundStatement(
                                                                                                                                                                new LockStatement("q"),
                                                                                                                                                                new CompoundStatement(
                                                                                                                                                                        new PrintStatement(new HeapReadingExpression(new VariableNameExpression("v2"))),
                                                                                                                                                                        new UnlockStatement("q"))))))))))))))))))));
        try {
            statement13.typeCheck(new Dictionary<>());
        }
        catch (CustomException e) {
            System.out.println("Error while typechecking program 13: " + e.getMessage() + "\n");
        }
        ProgramState state13 = new ProgramState(new StackImplementation<>(), new Dictionary<>(), new List<>(), new Dictionary<>(), new Heap<>(), new LockImplementation<>(), statement13);
        Repository repository13 = new Repository("log13.txt");
        repository13.add(state13);
        Controller controller13 = new Controller(repository13);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExampleCommand("1",statement1.toString(),controller1));
        menu.addCommand(new RunExampleCommand("2",statement2.toString(),controller2));
        menu.addCommand(new RunExampleCommand("3",statement3.toString(),controller3));
        menu.addCommand(new RunExampleCommand("4",statement4.toString(),controller4));
        menu.addCommand(new RunExampleCommand("5",statement5.toString(),controller5));
        menu.addCommand(new RunExampleCommand("6",statement6.toString(),controller6));
        menu.addCommand(new RunExampleCommand("7",statement7.toString(),controller7));
        menu.addCommand(new RunExampleCommand("8",statement8.toString(),controller8));
        menu.addCommand(new RunExampleCommand("9",statement9.toString(),controller9));
        menu.addCommand(new RunExampleCommand("10",statement10.toString(),controller10));
        menu.addCommand(new RunExampleCommand("11",statement11.toString(),controller11));
        menu.addCommand(new RunExampleCommand("12",statement12.toString(),controller12));
        menu.addCommand(new RunExampleCommand("13",statement13.toString(),controller13));
        menu.show();
    }
}
