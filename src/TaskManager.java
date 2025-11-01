import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    private File file;

    public TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.file = new File("taskFile.json");
        taskList.add(new Task("Wash the car","todo", LocalDateTime.now(),LocalDateTime.now()));
        taskList.add(new Task("Wash the car","todo", LocalDateTime.now(),LocalDateTime.now()));
        taskList.add(new Task("Wash the car","in-progress", LocalDateTime.now(),LocalDateTime.now()));
    }

    public void addTask(String[] tasks){
        for(int i = 1 ; i < tasks.length ; i++){
            String description = tasks[i];
            taskList.add(new Task(description,"todo", LocalDateTime.now(),LocalDateTime.now()));
        }
    }

    public boolean deleteTask(String descriptionId) throws NumberFormatException{
        int id = Integer.parseInt(descriptionId);
        if(id <= 0 || id > taskList.size())
            return false;

        int index = id - 1;
        Task removeTask = taskList.get(index);

        System.out.println("Task Deleted Successfully: "+removeTask.getDescription());
        taskList.remove(index);
        return true;
    }

    public boolean updateTaskDescription(String descriptionId,String newDescription) throws NumberFormatException{
        int id = Integer.parseInt(descriptionId);
        if(id <= 0 || id > taskList.size())
            return false;

        int index = id - 1;
        Task updateTaskDescription = taskList.get(index);
        updateTaskDescription.setDescription(newDescription);
        return true;
    }
    public boolean taskInProgress(String description) throws NumberFormatException{
        int id = Integer.parseInt(description);
        if(id <= 0 || id > taskList.size())
            return false;

        int index = id - 1;
        Task updateTaskStatus = taskList.get(index);
        updateTaskStatus.setStatus("in-progress");
        return true;
    }

    public boolean taskDone(String description) throws NumberFormatException{
        int id = Integer.parseInt(description);
        if(id <= 0 || id > taskList.size())
            return false;

        int index = id - 1;
        Task updateTaskStatus = taskList.get(index);
        updateTaskStatus.setStatus("done");
        return true;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
