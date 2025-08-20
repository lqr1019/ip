import java.util.Scanner;

public class Siri {
    public static void main(String[] args) {
        //Generate from https://patorjk.com/software/taag/
        String siriLogo = "   _____ _      _ \n" +
                "  / ____(_)    (_)\n" +
                " | (___  _ _ __ _ \n" +
                "  \\___ \\| | '__| |\n" +
                "  ____) | | |  | |\n" +
                " |_____/|_|_|  |_|\n" +
                "                  \n" +
                "                  ";

        System.out.print("Hello from\n" + siriLogo);

        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        ConsoleLogger consoleLogger = new ConsoleLogger(taskManager);
        System.out.print("\n____________________________________________________________\n");
        consoleLogger.PrintGreet();
        System.out.print("____________________________________________________________\n");
        while (true) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            /*String[] parts = input.split(" ", 2);
            String keyword = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";*/
            int spaceIndex = input.indexOf(' ');
            String keyword = (spaceIndex == -1) ? input : input.substring(0, spaceIndex);
            String argument = (spaceIndex == -1) ? "" : input.substring(spaceIndex + 1);

            Command cmd = Command.fromKeyword(keyword);

            if (cmd == null) {
                // 👇 unrecognized command → treat whole input as a generic Task
                Task task = new Task(input);
                taskManager.addTask(task);
                consoleLogger.Echo(input);
                continue;
            }

            switch (cmd) {
                case MARK:
                    consoleLogger.mark(Integer.parseInt(argument));
                    break;

                case UNMARK:
                    consoleLogger.unmark(Integer.parseInt(argument));
                    break;

                case TODO:
                    ToDoTask todo = new ToDoTask(argument);
                    taskManager.addTask(todo);
                    consoleLogger.displayTask(todo);
                    break;

                case DEADLINE:
                    int SlashIndex = input.indexOf('/');
                    String desc = input.substring(spaceIndex + 1, SlashIndex);
                    String deadline = input.substring(SlashIndex + 1);
                    DeadlineTask deadlineTask = new DeadlineTask(desc, deadline);
                    taskManager.addTask(deadlineTask);
                    consoleLogger.displayTask(deadlineTask);
                    break;

                case EVENT:
                    int firstSlashIndex = input.indexOf('/');
                    int secondSlashIndex = input.indexOf('/', firstSlashIndex + 1);
                    String description = input.substring(0, firstSlashIndex);
                    String from = input.substring(firstSlashIndex + 1, secondSlashIndex);
                    String to = input.substring(secondSlashIndex + 1);
                    EventTask event = new EventTask(description, from, to);
                    taskManager.addTask(event);
                    consoleLogger.displayTask(event);
                    break;

                case LIST:
                   consoleLogger.displayList();
                    break;

                case BYE:
                    consoleLogger.PrintExit();
                    sc.close();
                    return;
            }
        }


    }






}
