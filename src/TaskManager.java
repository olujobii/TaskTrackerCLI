import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    private Path path = Path.of("taskFile.json");

    public TaskManager(){
        taskList = new ArrayList<>();
        taskList.add(new Task("Wash","todo"));
        taskList.add(new Task("Iron","todo"));
    }

    public void addTask(String[] args){
        for(int i = 1 ; i < args.length ; i++){
            Task task = new Task(args[i],"todo");
            taskList.add(task);
            System.out.println("Task added successfully (ID:"+task.getId()+" )");
        }
    }

    public void deleteTask(String num){
        try{
            int id = Integer.parseInt(num);
            if(taskList.isEmpty()){
                System.out.println("No task has been created yet.");
                return;
            }

            if(id <= 0 || id > taskList.size())
                System.out.println("Task does not exist.");
            else{
                int index = id - 1;
                Task task = taskList.get(index);
                System.out.println("Task deleted successfully. ("+task.getDescription()+")");
                taskList.remove(index);
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid ID passed. ID must be a number");
        }
    }

    public void listTask(){
        if(taskList.isEmpty()){
            System.out.println("No task has been created yet.");
            return;
        }

        for(Task task : taskList){
            System.out.println("\n--------------------");
            System.out.println("ID: "+task.getId());
            System.out.println("Description: "+task.getDescription());
            System.out.println("Status: "+task.getStatus());
            System.out.println("--------------------");
        }
    }

    public void saveTaskToJsonFile(){
        try{
            if(!Files.exists(path)){
                Files.createFile(path);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[\n");
            for(int i = 0 ; i < taskList.size() ; i++){
                Task task = taskList.get(i);
                sb.append("\t{\n");
                sb.append("\t\t\"id\": \""+task.getId()+"\",\n");
                sb.append("\t\t\"description\": \""+task.getDescription()+"\",\n");
                sb.append("\t\t\"status\": \""+task.getStatus()+"\"\n");
                if(taskList.size() - 1 == i)
                    sb.append("\t}\n");
                else
                    sb.append("\t},\n");
            }
            sb.append("]");
            String jsonContent = sb.toString();
            Files.writeString(path,jsonContent);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
