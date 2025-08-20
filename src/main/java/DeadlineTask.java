public class DeadlineTask extends Task{
    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String display() {
        if (super.isDone()) {
            return "[D][X] " + super.getDescription() + " (" + deadline + ")";
        } else {
            return "[D][ ] " + super.getDescription() + " (" + deadline + ")";
        }
    }
}
