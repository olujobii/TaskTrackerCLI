import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        if(args.length == 0)
        {
            ConsoleUI.errorMessage("Please enter the command");
            return;
        }
        var applogic = new AppLogic(new TaskManager(new ArrayList<Task>()));
        applogic.run(args);
    }
}
