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

/**
 * Main class for Siri
 */
public class Siri {
    private final Storage storage;
    private final ConsoleLogger consoleLogger;
    private final TaskManager taskManager;
    private StringBuilder response;

    public Siri(String path) {
        this.storage = new Storage(path);
        this.taskManager = new TaskManager();
        this.consoleLogger = new ConsoleLogger(taskManager);
    }

    /**
     * Starting function of the app
     * @param args
     */
    public static void main(String[] args) {
        Siri siri = new Siri("./data/data.txt");
        //Generate from https://patorjk.com/software/taag/
        siri.run();
    }

    /**
     * Run the CLI
     */
    public void run() {
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
                        response = consoleLogger.mark(n);
                        storage.save(taskManager.getTasks());
                        break;
                    }

                    case UNMARK: {
                        int n = parser.parseUnMark();
                        response = consoleLogger.unmark(n);
                        storage.save(taskManager.getTasks());
                        break;
                    }

                    case DELETE: {
                        int n = parser.parseDelete();
                        response = consoleLogger.delete(n);
                        storage.save(taskManager.getTasks());
                    }

                    case TODO: {
                        String parse = parser.parseTodo();
                        ToDoTask todo = new ToDoTask(parse);
                        taskManager.addTask(todo);
                        response = consoleLogger.displayTask(todo);
                        storage.save(taskManager.getTasks());
                        break;
                    }

                    case DEADLINE: {
                        String[] parse = parser.parseDeadline();
                        DeadlineTask deadlineTask = new DeadlineTask(parse[0], parse[1]);
                        taskManager.addTask(deadlineTask);
                        response = consoleLogger.displayTask(deadlineTask);
                        storage.save(taskManager.getTasks());
                        break;
                    }

                    case EVENT: {
                        String[] parse = parser.parseEvent();;
                        EventTask event = new EventTask(parse[0], parse[1], parse[2]);
                        taskManager.addTask(event);
                        response = consoleLogger.displayTask(event);
                        storage.save(taskManager.getTasks());
                        break;
                    }

                    case LIST: {
                        response = consoleLogger.displayList();
                        break;
                    }

                    case FIND: {
                        String description = parser.parseFind();
                        List<Task> list = taskManager.findTask(description);
                        response = consoleLogger.displayFind(list);
                        break;
                    }

                    case BYE: {
                        response = consoleLogger.PrintExit();
                        sc.close();
                        return;
                    }
                }
            } catch (SiriException e) {
                ConsoleLogger.printLine("Error: " + e.getMessage());
            }
        }

    }

    /**
     * Gets the response string output by the console for GUI
     * @param input input String
     * @return the response string of input
     */
    public String getResponse(String input) {
        String trimmed = (input == null) ? "" : input.trim();
        if (trimmed.isEmpty()) return "";

        StringBuilder out = new StringBuilder();
        Parser parser = new Parser(trimmed);
        Command cmd = parser.getCommand();

        try {
            if (cmd == null) {
                throw new SiriException("Unknown command " + parser.getKeyword());
            }

            switch (cmd) {
                case LIST -> out.append(consoleLogger.displayList());
                case TODO -> {
                    String desc = parser.parseTodo();
                    ToDoTask todo = new ToDoTask(desc);
                    taskManager.addTask(todo);
                    out.append(consoleLogger.displayTask(todo));
                }
                case DEADLINE -> {
                    String[] p = parser.parseDeadline();
                    DeadlineTask d = new DeadlineTask(p[0], p[1]);
                    taskManager.addTask(d);
                    out.append(consoleLogger.displayTask(d));
                    storage.save(taskManager.getTasks());
                }
                case EVENT -> {
                    String[] p = parser.parseEvent();
                    EventTask e = new EventTask(p[0], p[1], p[2]);
                    taskManager.addTask(e);
                    out.append(consoleLogger.displayTask(e));
                    storage.save(taskManager.getTasks());
                }
                case MARK -> {
                    int idx = parser.parseMark();
                    out.append(consoleLogger.mark(idx));
                    storage.save(taskManager.getTasks());
                }
                case UNMARK -> {
                    int idx = parser.parseUnMark();
                    out.append(consoleLogger.unmark(idx));
                    storage.save(taskManager.getTasks());
                }
                case DELETE -> {
                    int idx = parser.parseDelete();
                    out.append(consoleLogger.delete(idx));
                    storage.save(taskManager.getTasks());
                }
                case FIND -> {
                    String kw = parser.parseFind();
                    List<Task> hits = taskManager.findTask(kw);
                    out.append(consoleLogger.displayFind(hits));
                }
                case BYE -> out.append(consoleLogger.PrintExit());
                default -> throw new SiriException("Unknown command " + parser.getKeyword());
            }
        } catch (SiriException e) {
            out.append(ConsoleLogger.printLine("Error: " + e.getMessage()));
        }
        return out.toString();
    }

    /**
     * Get the welcome String with logo
     * @return String of welcome word and logo
     */
    public String getWelcome() {
        String siriLogo = "   _____ _      _ \n" +
                "  / ____(_)    (_)\n" +
                " | (___  _ _ __ _ \n" +
                "  \\___ \\| | '__| |\n" +
                "  ____) | | |  | |\n" +
                " |_____/|_|_|  |_|\n" +
                "                  \n" +
                "                  ";

        return "Hello from\n" + siriLogo;
    }
}
