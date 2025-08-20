public enum Command {
    ECHO("echo"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    BYE("bye");   // 👈 new keyword

    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public static Command fromKeyword(String keyword) {
        for (Command cmd : Command.values()) {
            if (cmd.keyword.equalsIgnoreCase(keyword)) {
                return cmd;
            }
        }
        return null;
    }

}
