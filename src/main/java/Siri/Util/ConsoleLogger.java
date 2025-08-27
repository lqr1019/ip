package Siri.Util;

import Siri.Exception.SiriException;
import Siri.Task.Task;

public class ConsoleLogger {
    private TaskManager taskManager;

    public ConsoleLogger(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
    /**
     Prints a greeting message from Siri.Siri to the console.
     */
    public void PrintGreet() {
        System.out.print(" Hello! I'm Siri.Siri\n" + " What can I do for you?\n");
    }

    /**
     Prints an exit message from Siri.Siri to the console.
     */
    public void PrintExit() {
        System.out.print(" Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints back the given word to the console as an echo message.
     *
     * @param word the input string to be echoed
     */
    public void Echo(String word) {
        System.out.print("Added: " + word + "\n");
    }

    /**
     *  Prints every task stored in the taskManger
     */
    public void displayList() {
        System.out.print("____________________________________________________________\n");
        System.out.print("Here are the tasks in your list:\n");
        for (int i = 0; i < this.taskManager.getCount(); i ++) {
            System.out.print(i + 1 +". " + taskManager.getTasks().get(i).display() +"\n");
        }
        System.out.print("____________________________________________________________\n");
    }

    /**
     * mark a task
     * @param index the index of the task
     */
    public void mark(int index) {
        if (index > taskManager.getCount()) {
            throw new SiriException("Siri.Task.Task does not exist");
        }
        Task t = taskManager.getTasks().get(index - 1);
        t.setDone(true);
        System.out.print("____________________________________________________________\n");
        System.out.print("  Nice! I've marked this task as done:\n");
        System.out.print("  ");
        System.out.print(t.display());
        System.out.print("\n____________________________________________________________\n");
    }

    /**
     * unmark a task
     * @param index the index of the task
     */
    public void unmark(int index) {
        if (index > taskManager.getCount()) {
            throw new SiriException("Siri.Task.Task does not exist");
        }
        Task t = taskManager.getTasks().get(index - 1);
        t.setDone(false);
        System.out.print("____________________________________________________________\n");
        System.out.print("  OK, I've marked this task as not done yet: \n");
        System.out.print("  ");
        System.out.print(t.display());
        System.out.print("\n____________________________________________________________\n");
    }

    /**
     * display the task added and show the number of tasks in the list
     * @param task the task added
     */
    public void displayTask(Task task) {
        System.out.print("____________________________________________________________\n");
        System.out.print("Got it. I've added this task:\n");
        System.out.print("  " + task.display() +"\n");
        System.out.print("Now you have " + taskManager.getCount() + " tasks in the list.\n");
        System.out.print("____________________________________________________________\n");

    }

    /**
     * print one line of words
     * @param word words printed
     */
    public static void printLine(String word) {
        System.out.print(word + "\n");
    }

    /**
     * display the task deleted and show the number of tasks in the list
     * @param index the index of the task deleted
     */
    public void delete(int index) {
        if (index > taskManager.getCount()) {
            throw new SiriException("Siri.Task.Task does not exist");
        }
        System.out.print("____________________________________________________________\n");
        System.out.print("Noted. I've removed this task:\n");
        System.out.print("  " + taskManager.getTasks().get(index).display() + "\n");
        taskManager.deleteTask(index);
        System.out.print("Now you have " + taskManager.getCount() + " tasks in the list.\n");
        System.out.print("____________________________________________________________\n");
    }


}
