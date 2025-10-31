import java.io.File;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    private File file;

    public TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.file = new File("taskFile.json");
        taskList.add(new Task("Wash the car","todo"));
    }

    public void addTask(String[] tasks){
        for(int i = 1 ; i < tasks.length ; i++){
            String description = tasks[i];
            taskList.add(new Task(description,"todo"));
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

}
