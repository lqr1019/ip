public class ToDoTask extends Task{
    private String description;
    private boolean isDone;

    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String display() {
        if (isDone) {
            return "[T][X] " + description;
        } else {
            return "[T][ ] " + description;
        }
    }
}
