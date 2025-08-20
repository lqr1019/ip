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

        Scanner sc = new Scanner(System.in);
        TaskManger taskManger = new TaskManger();
        ConsoleLogger consoleLogger = new ConsoleLogger(taskManger);
        System.out.print("\n____________________________________________________________\n");
        consoleLogger.PrintGreet();
        System.out.print("____________________________________________________________\n");
        boolean Exit = false;
        while (!Exit) {
            String word = sc.nextLine();

            System.out.print("____________________________________________________________\n");
            System.out.print("User input: " + word +"\n");
            System.out.print("____________________________________________________________\n");
            if (word.equals("bye")) {
                Exit = true;
            } else if (word.equals("list")){
                consoleLogger.displayList();
            } else {
                String cmd = word.substring(0, word.indexOf(' '));
                String index = word.substring(word.indexOf(' ') + 1);
                if (cmd.equals("mark")) {
                    consoleLogger.mark(Integer.parseInt(index));
                } else if (cmd.equals("unmark")) {
                    consoleLogger.unmark(Integer.parseInt(index));
                } else {
                    consoleLogger.Echo(word);
                    Task t = new Task(word);
                    taskManger.addTask(t);
                }
            }
        }
        consoleLogger.PrintExit();
        System.out.print("____________________________________________________________\n");


    }






}
