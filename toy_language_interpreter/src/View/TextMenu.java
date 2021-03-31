package View;

import java.util.*;

public class TextMenu {
    private final HashMap<String, Command> commands;

    public TextMenu() { this.commands = new HashMap<>(); }

    public void addCommand(Command c) { this.commands.put(c.getKey(),c); }

    public void printMenu() {
        String[] rows = new String[100];
        int n = 0;
        for (Command c: commands.values()) {
            rows[n++] = String.format("%4s : %s", c.getKey(), c.getDescription());
        }
        for(int i = 0; i < n - 1;i++) {
            for(int j = i + 1;j < n;j++) {
                if(rows[i].compareTo(rows[j]) > 0) {
                    String temp = rows[i];
                    rows[i] = rows[j];
                    rows[j] = temp;
                }
            }
        }
        for (String row: rows) {
            if(row != null) System.out.println(row);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            printMenu();
            System.out.println("Input the option: ");
            String key = scanner.nextLine();
            Command c = commands.get(key);
            if(c == null) {
                System.out.println("Invalid command!");
                continue;
            }
            c.execute();
        }
    }
}
