import java.time.LocalDateTime;
import java.util.List;

public class TaskManager {
    private final List<Task> taskList;

    public TaskManager(List<Task> taskList) {
        this.taskList = taskList;
        this.taskList.add(new Task("Wash my clothes","todo",LocalDateTime.now(),LocalDateTime.now()));
        this.taskList.add(new Task("Wash my clothes","todo",LocalDateTime.now(),LocalDateTime.now()));
        this.taskList.add(new Task("Wash my clothes","todo",LocalDateTime.now(),LocalDateTime.now()));
        this.taskList.add(new Task("Wash my clothes","todo",LocalDateTime.now(),LocalDateTime.now()));
        this.taskList.add(new Task("Wash my clothes","todo",LocalDateTime.now(),LocalDateTime.now()));
    }


    public List<Task> getTaskList() {
        return taskList;
    }

    public void writeTasks(){
        for(Task task : taskList){
            ConsoleUI.showMessage("\n--------------------------------");
            ConsoleUI.showMessage("Id: "+task.getId());
            ConsoleUI.showMessage("Description: "+task.getDescription());
            ConsoleUI.showMessage("Status: "+task.getStatus());
            ConsoleUI.showMessage("CreatedAt: "+task.getCreatedAt());
            ConsoleUI.showMessage("UpdatedAt: "+task.getUpdatedAt());

            ConsoleUI.showMessage("--------------------------------");
        }
    }
    public void addTasks(String[] args){
        for(int i = 1; i < args.length; i++){
            var task = new Task(args[i],"todo", LocalDateTime.now(),LocalDateTime.now());
            taskList.add(task);
        }
    }

    public boolean updateTask(int id,String updatedDescription){
        Task task = checkIfTaskExists(id);

        if(task == null)
            return false;

        task.setDescription(updatedDescription);
        return true;
    }

    public boolean deleteTask(int id){
        Task task = checkIfTaskExists(id);

        if(task == null)
            return false;

        int index = task.getId() - 1;
        taskList.remove(index);

        //Rearrange taskId
        for (int i = index; i < taskList.size(); i++) {
            int newId = i + 1;
            taskList.get(i).setId(newId);
        }
        return true;
    }

    public boolean updateTaskStatus(int id,String status){
        Task task = checkIfTaskExists(id);

        if(task == null)
            return false;

        task.setStatus(status);
        return true;
    }

    public Task checkIfTaskExists(int id){
        Task specificTask = null;

        //First check if id is less than 1 or if it is greater than size of taskList
        if(id <1 || id > taskList.size())
            return null;

        for (Task task : taskList) {
            if(task.getId() == id) {
                specificTask = task;
                break;
            }
        }

        return specificTask;
    }
}
