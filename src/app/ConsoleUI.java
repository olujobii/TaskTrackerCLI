package app;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsoleUI {
    private TaskManager taskManager;
    private String[] args;
    private File file;

    public ConsoleUI(String[] args) {
        this.file = new File("taskFile.json"); //Creating file
        this.taskManager = new TaskManager(loadTaskFromJsonFile());
        this.args = args;
    }

    private ArrayList<Task> loadTaskFromJsonFile(){
        ArrayList<Task> taskFromJsonFile = new ArrayList<>();

        if(!file.exists() || file.length() == 0){
            return taskFromJsonFile;
        }

        //Read file from JSON
        try(FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null){
                sb.append(line.trim());
            }

            String content = sb.substring(1,sb.length() -1); // To remove [ ,] braces.
            content = content.substring(1,content.length() - 1)
                    .replace("{","").trim(); //Removes outer { and } and also replaces other {
            String[] contentArray = content.split("},");

            for(String individualTask : contentArray){
                Task addTask = getTask(individualTask);
                taskFromJsonFile.add(addTask);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return taskFromJsonFile;
    }

    private static Task getTask(String individualTask) {
        String[] parts = individualTask.split(",");
        int id = 0;
        String description = "";
        String status = "";
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        for(String task : parts){
            String[] keyValue = task.split(":" , 2);
            String key = keyValue[0].replace("\"", "").trim();
            String value = keyValue[1].replace("\"", "").trim();
            switch(key){
                case "id":
                    id = Integer.parseInt(value);
                    break;
                case "description":
                    description = value;
                    break;
                case "status":
                    status = value;
                    break;
                case "createdAt":
                    createdAt = LocalDateTime.parse(value);
                    break;
                case "updatedAt":
                    updatedAt = LocalDateTime.parse(value);
                    break;
            }
        }
        Task addTask = new Task(id,description,status,createdAt,updatedAt);
        return addTask;
    }

    public void startApplication(){
        String command = args[0];
        switch(command){
            case "add" -> handleAdd();
            case "update" -> handleUpdatedDescription();
            case "delete" -> handleDelete();
            case "mark-in-progress" -> handleMarkInProgress();
            case "mark-done" -> handleMarkDone();
            case "list" -> listTasks();
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

    private void handleUpdatedDescription(){
        if(args.length != 3){
            System.out.println("Usage: <update> <new-description>");
            return;
        }
        try{
            boolean isDescriptionUpdated = taskManager.updateTaskDescription(args[1],args[2]);
            if(!isDescriptionUpdated){
                System.out.println("ID does not exist");
            }else{
                System.out.println("Description updated successfully");
            }
        }catch(NumberFormatException e){
            System.out.println("Not a valid ID");
        }
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
        }
    }

    private void handleMarkInProgress() {
        if(args.length != 2){
            System.out.println("Usage: <mark-in-progress> <id>");
            return;
        }
        try {
            boolean isStatusUpdated = taskManager.taskInProgress(args[1]);
            if (!isStatusUpdated) {
                System.out.println("ID does not exist");
            } else {
                System.out.println("Task Updated Successfully");
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid ID");
        }
    }

    private void handleMarkDone() {
        if(args.length != 2){
            System.out.println("Usage: <mark-done> <id>");
            return;
        }
        try {
            boolean isStatusUpdated = taskManager.taskDone(args[1]);
            if (!isStatusUpdated) {
                System.out.println("ID does not exist");
            } else {
                System.out.println("Task Updated Successfully");
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid ID");
        }
    }

    private void listTasks() {
        ArrayList<Task> tasks = taskManager.getTaskList();
        if(args.length == 1){
            listAllTasks(tasks);
            return;
        }

        if(args.length == 2 && args[1].equals("todo")){
            listAllTodoTasks(tasks);
            return;
        }

        if(args.length == 2 && args[1].equals("done")){
            listAllDoneTasks(tasks);
            return;
        }

        if(args.length == 2 && args[1].equals("in-progress")){
            listAllInProgressTasks(tasks);
            return;
        }
        System.out.println("Usage: <list>");
        System.out.println("Usage: <list> <todo>");
        System.out.println("Usage: <list> <done>");
        System.out.println("Usage: <list> <in-progress>");

    }

    private void listAllTasks(ArrayList<Task> tasks){
        if(tasks.isEmpty()){
            System.out.println("No task has been created yet.");
            return;
        }

        for(Task task : tasks){
            System.out.println("\n----------");
            System.out.println("ID: "+task.getId());
            System.out.println("Description: "+task.getDescription());
            System.out.println("Status: "+task.getStatus());
            System.out.println("Created at: "+task.getCreatedAt());
            System.out.println("Last updated: "+task.getUpdatedAt());
            System.out.println("----------");
        }
    }

    private void listAllTodoTasks(ArrayList<Task> tasks) {
        List<Task> todoTasks = tasks.stream()
                .filter(task -> task.getStatus().equals("todo")).toList();

        if(todoTasks.isEmpty()){
            System.out.println("No pending task yet");
            return;
        }

        for(Task task : todoTasks){
            System.out.println("\n----------");
            System.out.println("ID: "+task.getId());
            System.out.println("Description: "+task.getDescription());
            System.out.println("Status: "+task.getStatus());
            System.out.println("Created at: "+task.getCreatedAt());
            System.out.println("Last updated: "+task.getUpdatedAt());
            System.out.println("----------");
        }
    }
    private void listAllDoneTasks(ArrayList<Task> tasks){
        List<Task> doneTasks = tasks.stream()
                .filter(task -> task.getStatus().equals("done")).toList();

        if(doneTasks.isEmpty()){
            System.out.println("No task has been completed yet");
            return;
        }

        for(Task task : doneTasks){
            System.out.println("\n----------");
            System.out.println("ID: "+task.getId());
            System.out.println("Description: "+task.getDescription());
            System.out.println("Status: "+task.getStatus());
            System.out.println("Created at: "+task.getCreatedAt());
            System.out.println("Last updated: "+task.getUpdatedAt());
            System.out.println("----------");
        }
    }

    private void listAllInProgressTasks(ArrayList<Task> tasks) {
        List<Task> inProgressTasks = tasks.stream()
                .filter(task -> task.getStatus().equals("in-progress")).toList();

        if(inProgressTasks.isEmpty()){
            System.out.println("No task has been started yet");
            return;
        }

        for(Task task : inProgressTasks){
            System.out.println("\n----------");
            System.out.println("ID: "+task.getId());
            System.out.println("Description: "+task.getDescription());
            System.out.println("Status: "+task.getStatus());
            System.out.println("Created at: "+task.getCreatedAt());
            System.out.println("Last updated: "+task.getUpdatedAt());
            System.out.println("----------");
        }
    }

    public void saveTaskToJsonFile(){
        ArrayList<Task> taskList = taskManager.getTaskList();

        if(taskList.isEmpty()){
            return;
        }

        try(FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){

            //Parsing to JSON
            StringBuilder sb = getStringBuilder(taskList);
            String jsonContent = sb.toString();
            bufferedWriter.write(jsonContent);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private StringBuilder getStringBuilder(ArrayList<Task> taskList){
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(int i = 0 ; i < taskList.size() ; i++){
            Task task = taskList.get(i);
            sb.append("\t{\n");
            sb.append("\t\t\"id\": "+task.getId()+",\n");
            sb.append("\t\t\"description\": \""+task.getDescription()+"\",\n");
            sb.append("\t\t\"status\": \""+task.getStatus()+"\",\n");
            sb.append("\t\t\"createdAt\": \""+task.getCreatedAt()+"\",\n");
            sb.append("\t\t\"updatedAt\": \""+task.getUpdatedAt()+"\"\n");
            if(taskList.size() - 1 == i)
                sb.append("\t}\n");
            else
                sb.append("\t},\n");
        }
        sb.append("]");
        return sb;
    }


}
