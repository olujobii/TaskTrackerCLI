public class Main {
    public static void main(String[] args) {

        if(args.length == 0){
            System.out.println("No arguments passed, program shutting down");
            return;
        }

        switch(args[0]){
            case "add":
                System.out.println("Task added successfully");
                break;
            case "delete":
                System.out.println("Deleted successfully");
                break;
            case "update":
                System.out.println("Updated successfully");
                break;
            case "mark-in-progress":
                System.out.println("Mark In progress");
                break;
            case "mark-done":
                System.out.println("Mark done");
                break;
            case "list":
                System.out.println("List");
                break;
            default:
                System.out.println("Not a valid command");
                break;
        }
    }
}
