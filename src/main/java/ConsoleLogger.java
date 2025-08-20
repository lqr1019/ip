public class ConsoleLogger {
    private TaskManager taskManger;

    public ConsoleLogger(TaskManager taskManger) {
        this.taskManger = taskManger;
    }
    /**
     Prints a greeting message from Siri to the console.
     */
    public void PrintGreet() {
        System.out.print(" Hello! I'm Siri\n" + " What can I do for you?\n");
    }

    /**
     Prints an exit message from Siri to the console.
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
        for (int i = 0; i < this.taskManger.getCount(); i ++) {
            System.out.print(i + 1 +". " + taskManger.getTasks().get(i).display() +"\n");
        }
        System.out.print("____________________________________________________________\n");
    }

    /**
     * mark a task
     * @param index the index of the task
     */
    public void mark(int index) {
        Task t = taskManger.getTasks().get(index - 1);
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
        Task t = taskManger.getTasks().get(index - 1);
        t.setDone(false);
        System.out.print("____________________________________________________________\n");
        System.out.print("  OK, I've marked this task as not done yet: \n");
        System.out.print("  ");
        System.out.print(t.display());
        System.out.print("\n____________________________________________________________\n");
    }

    public void todo(ToDoTask task) {

    }

    public void deadline(DeadlineTask task) {

    }

    public void event(EventTask task) {

    }
}
