import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private Path taskListFile = Path.of("tasklist.json");
    public TaskManager(ArrayList<Task> task){
        this.tasks = task;
    }

    public void addTask(Task userTask){
        tasks.add(userTask);
        try {
            if (!Files.exists(taskListFile)) {
                Path createdFilePath = Files.createFile(taskListFile);
                System.out.println("File created at Path: "+createdFilePath.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
