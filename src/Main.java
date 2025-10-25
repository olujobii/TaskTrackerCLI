import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager(new ArrayList<Task>());

        if(args.length == 0){
            System.out.println("No arguments passed, program shutting down");
            return;
        }

        switch(args[0]){
            case "add":
                taskManager.addTask(new Task(args[1],"todo"));
                System.out.println("Task added successfully");
                break;
            case "delete":
                System.out.println("Deleted successfully");
                break;
            case "list":
                System.out.println("listing task");
                break;
            default:
                System.out.println("Not a valid command");
                break;
        }
    }
}
