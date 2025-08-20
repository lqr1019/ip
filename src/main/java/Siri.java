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
}
