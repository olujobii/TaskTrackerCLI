package app;

import java.time.LocalDateTime;
import java.util.ArrayList;

class TaskManager {
    private ArrayList<Task> taskList;

    protected TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    protected void addTask(String[] tasks){
        for(int i = 1 ; i < tasks.length ; i++){
            String description = tasks[i];
            taskList.add(new Task(description,"todo", LocalDateTime.now(),LocalDateTime.now()));
        }
    }

    protected boolean deleteTask(String descriptionId) throws NumberFormatException{
        int id = Integer.parseInt(descriptionId);
        if(id <= 0 || id > taskList.size())
            return false;

        int index = id - 1;
        Task removeTask = taskList.get(index);

        System.out.println("app.Task Deleted Successfully: "+removeTask.getDescription());
        taskList.remove(index);
        //Update task ID of others after removal
        for(int i = index ; i < taskList.size(); i ++){
            Task task = taskList.get(i);
            int newId = i + 1;
            task.setId(newId);
        }
        return true;
    }

    protected boolean updateTaskDescription(String descriptionId,String newDescription) throws NumberFormatException{
        int id = Integer.parseInt(descriptionId);
        if(id <= 0 || id > taskList.size())
            return false;

        int index = id - 1;
        Task updateTaskDescription = taskList.get(index);
        updateTaskDescription.setDescription(newDescription);
        updateTaskDescription.setUpdatedAt(LocalDateTime.now());
        return true;
    }
    protected boolean taskInProgress(String description) throws NumberFormatException{
        int id = Integer.parseInt(description);
        if(id <= 0 || id > taskList.size())
            return false;

        int index = id - 1;
        Task updateTaskStatus = taskList.get(index);
        updateTaskStatus.setStatus("in-progress");
        return true;
    }

    protected boolean taskDone(String description) throws NumberFormatException{
        int id = Integer.parseInt(description);
        if(id <= 0 || id > taskList.size())
            return false;

        int index = id - 1;
        Task updateTaskStatus = taskList.get(index);
        updateTaskStatus.setStatus("done");
        return true;
    }

    protected ArrayList<Task> getTaskList() {
        return taskList;
    }

}
