import app.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("No command found");
            return;
        }

        ConsoleUI consoleUI = new ConsoleUI(args);

        consoleUI.startApplication();
        consoleUI.saveTaskToJsonFile();
    }
}
