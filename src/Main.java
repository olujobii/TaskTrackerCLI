public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        if(args.length == 0){
            System.out.println("No arguments passed, program shutting down");
            return;
        }

        switch(args[0]){
            case "add":
                taskManager.addTask(args);
                break;
            case "delete":
                taskManager.deleteTask(args[1]);
                break;
            case "list":
                taskManager.listTask();
                break;
            default:
                System.out.println("Not a valid command");
                break;
        }

        taskManager.saveTaskToJsonFile();
    }
}
