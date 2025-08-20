public class DeadlineTask extends Task{
    private String description;
    private boolean isDone;
    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String display() {
        if (isDone) {
            return "[D][X] " + description + " (" + deadline + ")";
        } else {
            return "[D][ ] " + description + " (" + deadline + ")";
        }
    }
}
