import app.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("No argument passed");
            return;
        }

        ConsoleUI consoleUI = new ConsoleUI(args);

        consoleUI.startApplication();
        consoleUI.saveTaskToJsonFile();
    }
}
