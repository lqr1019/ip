public class ConsoleLogger {
    /**
     Prints a greeting message from Siri to the console.
     */
    public static void PrintGreet() {
        System.out.print(" Hello! I'm Siri\n" + " What can I do for you?\n");
    }

    /**
     Prints an exit message from Siri to the console.
     */
    public static void PrintExit() {
        System.out.print(" Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints back the given word to the console as an echo message.
     *
     * @param word the input string to be echoed
     */
    public static void Echo(String word) {
        System.out.print("Added: " + word + "\n");
    }

    public static void displayList(TaskManger taskManger) {
        System.out.print("____________________________________________________________\n");
        System.out.print("Here are the tasks in your list:\n");
        for (int i = 0; i < taskManger.getCount(); i ++) {
            System.out.print(i + 1 +". " + taskManger.getTasks().get(i).display() +"\n");
        }
        System.out.print("____________________________________________________________\n");
    }
}
