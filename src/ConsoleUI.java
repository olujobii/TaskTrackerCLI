public class ConsoleUI {
    private TaskManager taskManager;
    private String[] args;

    public ConsoleUI(TaskManager taskManager, String[] args) {
        this.taskManager = taskManager;
        this.args = args;
    }


    public void startApplication(){
        String command = args[0];
        switch(command){
            case "add" -> handleAdd();
            case "update" -> handleUpdate();
            case "delete" -> handleDelete();
            case "mark-in-progress" -> handleMarkInProgress();
            case "mark-done" -> handleMarkDone();
            case "list" -> listAllTasks();
            default -> System.out.println("Incorrect command");
        }
    }

    private void handleAdd(){
        if(args.length < 2){
            System.out.println("Usage: <add> <description>");
            return;
        }
        taskManager.addTask(args);
        System.out.println("Task added successfully");

    }

    private void handleUpdate(){
        if(args.length != 3){
            System.out.println("Usage: <update> <new-description>");
            return;
        }
        System.out.println("Updated Successfully");
    }

    private void handleDelete() {
        if(args.length != 2){
            System.out.println("Usage: <delete> <id>");
            return;
        }
        try {
            boolean isTaskDeleted = taskManager.deleteTask(args[1]);

            if(!isTaskDeleted){
                System.out.println("ID does not exist");
            }
        } catch (NumberFormatException e) {
            System.err.println("Not a valid ID");
            e.printStackTrace();
        }
    }

    private void handleMarkInProgress() {
        if(args.length != 2){
            System.out.println("Usage: <mark-in-progress> <id>");
            return;
        }

        System.out.println("MARK-IN-PROGRESS");
    }

    private void handleMarkDone() {
        if(args.length != 2){
            System.out.println("Usage: <mark-done> <id>");
            return;
        }

        System.out.println("MARK-DONE");
    }

    private void listAllTasks() {
        System.out.println("LIST ALL TASKS");
    }
}
