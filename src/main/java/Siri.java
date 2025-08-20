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

            String[] parts = input.split(" ", 2);
            String keyword = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";

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
                    consoleLogger.todo(todo);
                    break;

                case DEADLINE:
                    String[] deadlineParts = argument.split("/by", 2);
                    DeadlineTask deadline = new DeadlineTask(deadlineParts[0].trim(), deadlineParts[1].trim());
                    taskManager.addTask(deadline);
                    consoleLogger.deadline(deadline);
                    break;

                case EVENT:
                    String desc = argument.split("/from")[0].trim();
                    String from = argument.split("/from")[1].split("/to")[0].trim();
                    String to = argument.split("/to")[1].trim();
                    EventTask event = new EventTask(desc, from, to);
                    taskManager.addTask(event);
                    consoleLogger.event(event);
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
