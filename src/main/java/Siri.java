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
        ConsoleLogger consoleLogger = new ConsoleLogger();
        System.out.print("\n____________________________________________________________\n");
        ConsoleLogger.PrintGreet();
        System.out.print("____________________________________________________________\n");
        int index = 0;
        boolean Exit = false;
        while (!Exit) {
            String word = sc.nextLine();

            System.out.print("____________________________________________________________\n");
            System.out.print("User input: " + word +"\n");
            System.out.print("____________________________________________________________\n");
            if (word.equals("bye")) {
                Exit = true;
            } else if (word.equals("list")){
                ConsoleLogger.displayList(taskManger);
            }else {
                ConsoleLogger.Echo(word);
                Task t = new Task(word);
                taskManger.addTask(t);
            }
        }
        ConsoleLogger.PrintExit();
        System.out.print("____________________________________________________________\n");


    }






}
