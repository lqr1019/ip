package Siri;

import Siri.Exception.SiriException;
import Siri.Task.DeadlineTask;
import Siri.Task.EventTask;
import Siri.Task.Task;
import Siri.Task.ToDoTask;
import Siri.Util.ConsoleLogger;
import Siri.Util.Parser;
import Siri.Util.Storage;
import Siri.Util.TaskManager;

import java.util.List;
import java.util.Scanner;

public class Siri {
    private static final String DATA_PATH = "./data/data.txt";
    private static final Storage STORAGE = new Storage(DATA_PATH);
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

            Parser parser = new Parser(input);
            String keyword = parser.getKeyword();
            String argument = parser.getArgument();
            Command cmd = parser.getCommand();
            try{
                if (cmd == null) {
                    //unrecognised comment will throw an exception
                    throw new SiriException("Unknown command " + keyword);
                }

                switch (cmd) {
                    case MARK: {
                        int n = parser.parseMark();
                        consoleLogger.mark(n);
                        STORAGE.save(taskManager.getTasks());
                        break;
                    }

                    case UNMARK: {
                       int n = parser.parseUnMark();
                        consoleLogger.unmark(n);
                        STORAGE.save(taskManager.getTasks());
                        break;
                    }

                    case DELETE: {
                        int n = parser.parseDelete();
                        consoleLogger.delete(n);
                        STORAGE.save(taskManager.getTasks());
                    }

                    case TODO: {
                        String parse = parser.parseTodo();
                        ToDoTask todo = new ToDoTask(parse);
                        taskManager.addTask(todo);
                        consoleLogger.displayTask(todo);
                        STORAGE.save(taskManager.getTasks());
                        break;
                    }

                    case DEADLINE: {
                        String[] parse = parser.parseDeadline();
                        DeadlineTask deadlineTask = new DeadlineTask(parse[0], parse[1]);
                        taskManager.addTask(deadlineTask);
                        consoleLogger.displayTask(deadlineTask);
                        STORAGE.save(taskManager.getTasks());
                        break;
                    }

                    case EVENT: {
                        String[] parse = parser.parseEvent();;
                        EventTask event = new EventTask(parse[0], parse[1], parse[2]);
                        taskManager.addTask(event);
                        consoleLogger.displayTask(event);
                        STORAGE.save(taskManager.getTasks());
                        break;
                    }

                    case LIST: {
                        consoleLogger.displayList();
                        break;
                    }

                    case FIND: {
                        String description = parser.parseFind();
                        List<Task> list = taskManager.findTask(description);
                        consoleLogger.displayFind(list);
                        break;
                    }



                    case BYE: {
                        consoleLogger.PrintExit();
                        sc.close();
                        return;
                    }
                }
            } catch (SiriException e) {
                ConsoleLogger.printLine("Error: " + e.getMessage());
            }
        }


    }






}
