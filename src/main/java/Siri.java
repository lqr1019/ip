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

            int spaceIndex = input.indexOf(' ');
            String keyword = (spaceIndex == -1) ? input : input.substring(0, spaceIndex);
            String argument = (spaceIndex == -1) ? "" : input.substring(spaceIndex + 1);

            Command cmd = Command.fromKeyword(keyword);
            try{
                if (cmd == null) {
                    //unrecognised comment will treat as adding new general task
                    Task task = new Task(input);
                    taskManager.addTask(task);
                    consoleLogger.Echo(input);
                    continue;
                }

                switch (cmd) {
                    case MARK: {
                        if (argument.isEmpty()) {
                            throw new SiriException(
                                    "Missing task number for mark",
                                    "mark <number>",
                                    input
                            );
                        }
                        int n;
                        try {
                            n = Integer.parseInt(argument);
                        } catch (NumberFormatException ex) {
                            throw new SiriException(
                                    "Task number must be an integer",
                                    "mark <number>",
                                    argument
                            );
                        }
                        consoleLogger.mark(n);
                        break;
                    }

                    case UNMARK: {
                        if (argument.isEmpty()) {
                            throw new SiriException(
                                    "Missing task number for mark",
                                    "unmark <number>",
                                    input
                            );
                        }
                        int n;
                        try {
                            n = Integer.parseInt(argument);
                        } catch (NumberFormatException ex) {
                            throw new SiriException(
                                    "Task number must be an integer",
                                    "unmark <number>",
                                    argument
                            );
                        }
                        consoleLogger.unmark(n);
                        break;
                    }

                    case DELETE: {
                        if (argument.isEmpty()) {
                            throw new SiriException(
                                    "Missing task number for mark",
                                    "delete <number>",
                                    input
                            );
                        }
                        int n;
                        try {
                            n = Integer.parseInt(argument);
                        } catch (NumberFormatException ex) {
                            throw new SiriException(
                                    "Task number must be an integer",
                                    "delete <number>",
                                    argument
                            );
                        }
                        consoleLogger.delete(n);
                    }

                    case TODO: {
                        if (argument.isEmpty()) {
                            throw new SiriException("Todo task description is empty", "todo <description>", argument);
                        }
                        ToDoTask todo = new ToDoTask(argument);
                        taskManager.addTask(todo);
                        consoleLogger.displayTask(todo);
                        break;
                    }

                    case DEADLINE: {
                        int SlashIndex = input.indexOf('/');
                        if (SlashIndex < 0) {
                            throw new SiriException("Missing '/' separator for deadline task", "deadline <description> / <time>", argument);
                        }
                        String desc = input.substring(spaceIndex + 1, SlashIndex);
                        String deadline = input.substring(SlashIndex + 1);
                        if (desc.isEmpty() || deadline.isEmpty()) {
                            throw new SiriException("Deadline description or date is empty", "deadline <description> / <time>", argument);
                        }
                        DeadlineTask deadlineTask = new DeadlineTask(desc, deadline);
                        taskManager.addTask(deadlineTask);
                        consoleLogger.displayTask(deadlineTask);
                        break;
                    }

                    case EVENT: {
                        int firstSlashIndex = input.indexOf('/');
                        int secondSlashIndex = input.indexOf('/', firstSlashIndex + 1);
                        if (firstSlashIndex < 0 || secondSlashIndex < 0) {
                            throw new SiriException("Missing '/' separator for event task", "event <description> / <start> / <end>", argument);
                        }
                        String description = input.substring(0, firstSlashIndex);
                        String from = input.substring(firstSlashIndex + 1, secondSlashIndex);
                        String to = input.substring(secondSlashIndex + 1);
                        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                            throw new SiriException("Event description, start, or end is empty", "event <description> / <start> / <end>", argument);
                        }
                        EventTask event = new EventTask(description, from, to);
                        taskManager.addTask(event);
                        consoleLogger.displayTask(event);
                        break;
                    }

                    case LIST: {
                        consoleLogger.displayList();
                        break;
                    }



                    case BYE: {
                        consoleLogger.PrintExit();
                        sc.close();
                        return;
                    }
                }
            } catch (SiriException e) {
                consoleLogger.printLine("Error: " + e.getMessage());
            }
        }


    }






}
