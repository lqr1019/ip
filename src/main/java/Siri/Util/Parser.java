package Siri.Util;

import Siri.Command;
import Siri.Exception.SiriException;

public class Parser {
    private Command command;
    private String description;
    private String argument;
    private String keyword;
    private String deadline;
    private String from;
    private String to;
    private int spaceIndex;

    /**
     * Constructor of parser, determine the keyword and argument from the input
     * @param input the input contents
     */
    public Parser(String input) {
        this.spaceIndex = input.indexOf(' ');
        this.keyword = (spaceIndex == -1) ? input : input.substring(0, spaceIndex);
        this.argument = (spaceIndex == -1) ? "" : input.substring(spaceIndex + 1);
    }

    /**
     * get command from the keyword
     * @return command
     */
    public Command getCommand() {
        return Command.fromKeyword(keyword);
    }

    /**
     * Getter of keyword
     * @return keyword
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Getter of argument
     * @return argument
     */
    public String getArgument() {
        return this.argument;
    }

    /**
     * Parsing ToDoTask
     * @return the description of the ToDoTask if the argument is valid
     * @throws SiriException if the argument is empty, throw an exception
     */
    public String parseTodo() throws SiriException {
        if (argument.isEmpty()) {
            throw new SiriException("Todo task description is empty", "todo <description>", argument);
        } else {
            return argument;
        }
    }

    /**
     * Parsing mark action
     * @return the index of the task being marked
     * @throws SiriException if the index is invalid, throw an exception
     */
    public int parseMark() throws SiriException {
        if (argument.isEmpty()) {
            throw new SiriException(
                    "Missing task number for mark",
                    "mark <number>",
                    ""
            );
        }
        int n;
        try {
            n = Integer.parseInt(argument);
        } catch (NumberFormatException ex) {
            throw new SiriException(
                    "Siri.Task.Task number must be an integer",
                    "mark <number>",
                    argument
            );
        }
        return n;
    }

    /**
     * Parsing unmark action
     * @return the index of the task being unmarked
     * @throws SiriException if the index is invalid, throw an exception
     */
    public int parseUnMark() throws SiriException {
        if (argument.isEmpty()) {
            throw new SiriException(
                    "Missing task number for mark",
                    "unmark <number>",
                    ""
            );
        }
        int n;
        try {
            n = Integer.parseInt(argument);
        } catch (NumberFormatException ex) {
            throw new SiriException(
                    "Siri.Task.Task number must be an integer",
                    "unmark <number>",
                    argument
            );
        }
        return n;
    }

    /**
     * Parsing delete action
     * @return the index of the task being deleted
     * @throws SiriException if the index is invalid, throw an exception
     */
    public int parseDelete() throws SiriException {
        if (argument.isEmpty()) {
            throw new SiriException(
                    "Missing task number for mark",
                    "delete <number>",
                    ""
            );
        }
        int n;
        try {
            n = Integer.parseInt(argument);
        } catch (NumberFormatException ex) {
            throw new SiriException(
                    "Siri.Task.Task number must be an integer",
                    "delete <number>",
                    argument
            );
        }
        return n;
    }

    /**
     * Parsing deadline task
     * @return a String array where the first element is the description adn the second is the deadline
     * @throws SiriException if the argument is invalid, throw an exception
     */
    public String[] parseDeadline() throws SiriException {
        final String MARKER = "/by";
        if (argument == null) argument = "";
        String arg = argument.trim();
        int byIdx = arg.toLowerCase().indexOf(MARKER);
        if (byIdx < 0) {
            throw new SiriException(
                    "Missing '/by' marker for deadline task",
                    "deadline <description> /by <time>",
                    argument
            );
        }
        description = arg.substring(0, byIdx).trim();
        deadline   = arg.substring(byIdx + MARKER.length()).trim();

        if (description.isEmpty() || deadline.isEmpty()) {
            throw new SiriException(
                    "Deadline description or time is empty",
                    "deadline <description> /by <time>",
                    argument
            );
        }
        return new String[]{description, deadline};
    }

    /**
     * Parsing EventTask
     * @return a String array where the first element is the description, the second is the starting time, the third is the ending time
     * @throws SiriException if the argument is invalid, throw an exception
     */
    public String[] parseEvent() throws SiriException {
        if (argument == null) argument = "";
        int firstSlashIndex = argument.indexOf('/');
        int secondSlashIndex = argument.indexOf('/', firstSlashIndex + 1);
        if (firstSlashIndex < 0 || secondSlashIndex < 0) {
            throw new SiriException("Missing '/' separator for event task", "event <description> / <start> / <end>", argument);
        }
        description = argument.substring(0, firstSlashIndex);
        from = argument.substring(firstSlashIndex + 1, secondSlashIndex);
        to = argument.substring(secondSlashIndex + 1);
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new SiriException("Event description, start, or end is empty", "event <description> / <start> / <end>", argument);
        }
        return new String[] {description, from, to};
    }
}
