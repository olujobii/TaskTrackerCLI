import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(new ArrayList<Task>());
        ConsoleUI consoleUI = new ConsoleUI(taskManager,args);

        if(args.length == 0) {
            System.out.println("No argument passed");
            return;
        }
        consoleUI.startApplication();
    }
}
