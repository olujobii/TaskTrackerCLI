public class AppLogic {
    private final TaskManager taskManager;

    public AppLogic(TaskManager taskManager){
        this.taskManager = taskManager;
    }
    public void run(String[] args){
        String command = args[0];
        boolean isOperationSuccessful = false;
        switch(command){
            case "add":
                isOperationSuccessful = handleAdd(args);
                break;
            case "update":
                isOperationSuccessful = handleUpdate(args);
                break;
            case "delete":
               isOperationSuccessful = handleDelete(args);
                break;
            case "mark-in-progress":
                isOperationSuccessful = handleMarkInProgress(args);
                break;
            case "mark-done":
                isOperationSuccessful = handleMarkDone(args);
                break;
            case "list":
                listTaskOperation();
                break;
            default:
               ConsoleUI.errorMessage("Unknown command");
                break;
        }

        if(isOperationSuccessful)
            taskManager.writeTasks();
    }

    private boolean handleAdd(String[] args) {
        if(args.length < 2){
            ConsoleUI.errorMessage("Wrong number of arguments");
            return false;
        }
        taskManager.addTasks(args);
        ConsoleUI.showMessage("Task Added");
        return true;
    }

    private boolean handleUpdate(String[] args) {
        if(args.length != 3){
            ConsoleUI.errorMessage("Wrong number of arguments");
            return false;
        }
        String userInputId = args[1];
        String updatedDescription = args[2];
        int id;

        try{
            id = convertIdStringToInteger(userInputId);
        } catch (NumberFormatException e) {
            ConsoleUI.errorMessage("Not a valid ID");
            return false;
        }

        boolean isTaskUpdated = taskManager.updateTask(id,updatedDescription);
        if(!isTaskUpdated){
            ConsoleUI.errorMessage("Task does not exist");
            return false;
        }

        ConsoleUI.showMessage("Task updated successfully");
        return true;
    }

    private boolean handleDelete(String[] args) {
        if(args.length != 2){
            ConsoleUI.errorMessage("Wrong number of arguments");
            return false;
        }

        String userInputId = args[1];
        int id;

        try{
            id = convertIdStringToInteger(userInputId);
        }catch(NumberFormatException ex)
        {
            ConsoleUI.errorMessage("Not a valid ID");
            return false;
        }

        boolean isTaskDeleted = taskManager.deleteTask(id);
        if(!isTaskDeleted){
            ConsoleUI.errorMessage("Task does not exist");
            return false;
        }

        ConsoleUI.showMessage("Task deleted successfully");
        return true;
    }

    private boolean handleMarkInProgress(String[] args) {
        if(args.length != 2){
            ConsoleUI.errorMessage("Invalid number of arguments");
            return false;
        }

        String userInputId = args[1];
        int id;

        try{
            id = convertIdStringToInteger(userInputId);
        } catch (NumberFormatException e) {
            ConsoleUI.errorMessage("Not a valid ID");
            return false;
        }

        boolean isTaskMarkedInProgress = taskManager.updateTaskStatus(id,"in-progress");

        if(!isTaskMarkedInProgress){
            ConsoleUI.errorMessage("Task does not exist");
            return false;
        }

        ConsoleUI.showMessage("Task has been updated successfully");
        return true;
    }

    private boolean handleMarkDone(String[] args) {
        if(args.length != 2){
            ConsoleUI.errorMessage("Invalid number of arguments");
            return false;
        }

        String userInputId = args[1];
        int id;

        try{
            id = convertIdStringToInteger(userInputId);
        } catch (NumberFormatException e) {
            ConsoleUI.errorMessage("Not a valid ID");
            return false;
        }

        boolean isTaskMarkedInProgress = taskManager.updateTaskStatus(id,"done");

        if(!isTaskMarkedInProgress){
            ConsoleUI.errorMessage("Task does not exist");
            return false;
        }

        ConsoleUI.showMessage("Task has been updated successfully");
        return true;
    }

    private int convertIdStringToInteger(String id)throws NumberFormatException{
        return Integer.parseInt(id);
    }

    private void listTaskOperation() {
    }
}
