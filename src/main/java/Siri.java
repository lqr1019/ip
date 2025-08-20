import java.util.Objects;
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
        System.out.print("\n____________________________________________________________\n");
        PrintGreet();
        System.out.print("____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        TaskManger taskManger = new TaskManger();
        int index = 0;
        boolean Exit = false;
        while (!Exit) {
            String word = sc.next();

            System.out.print("____________________________________________________________\n");
            System.out.print("User input: " + word +"\n");
            System.out.print("____________________________________________________________\n");
            if (word.equals("bye")) {
                Exit = true;
            } else if (word.equals("list")){
                taskManger.displayList();
            }else {
                Echo(word);
                Task t = new Task(word);
                taskManger.addTask(t);
            }
        }
        PrintExit();
        System.out.print("____________________________________________________________\n");


    }

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
        System.out.print("Echoing input " + word + "\n");
    }

    /**
     * Display the words stored in the list
     * @param list the stored list of text entered
     * @param index the current index of the list
     */
    public static void display(String[] list, int index) {
        for (int i = 0; i < index; i ++) {
            System.out.print(i + 1 + ". " + list[i] +"\n");
        }
        System.out.print("____________________________________________________________\n");
    }
}
