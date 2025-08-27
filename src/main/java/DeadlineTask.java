public class DeadlineTask extends Task{
    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of this deadline task,
     * including its type marker "[D]", completion status,
     * description, and deadline information.
     *
     * <p>If the task is marked as done, "[X]" is shown;
     * otherwise "[ ]" is shown.</p>
     *
     * @return a formatted string representing the deadline task
     *         (e.g., "[D][ ] return book (by: Sunday)")
     */
    @Override
    public String display() {
        if (super.isDone()) {
            return "[D][X] " + super.getDescription() + " (" + deadline + ")";
        } else {
            return "[D][ ] " + super.getDescription() + " (" + deadline + ")";
        }
    }

    public String getDeadline() {
        return this.deadline;
    }
}
