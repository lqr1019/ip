package siri.task;

public class EventTask extends Task {
    private String from;
    private String to;

    /**
     * Constructor of EventTask
     * @param description description of the task
     * @param from the starting point
     * @param to the ending point
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of this event task,
     * including its type marker "[E]", completion status,
     * description, start time, and end time.
     *
     * <p>If the task is marked as done, "[X]" is shown;
     * otherwise "[ ]" is shown.</p>
     *
     * @return a formatted string representing the event task
     *         (e.g., "[E][ ] project meeting (from: Mon 2pm to: 4pm)")
     */
    @Override
    public String display() {
        if (super.isDone()) {
            return "[E][X] " + super.getDescription() + " (" + from + to +")";
        } else {
            return "[E][ ] " + super.getDescription() + " (" + from + to +")";
        }
    }

    /**
     * Getter of from
     * @return from
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Getter of to
     * @return to
     */
    public String getTo() {
        return this.to;
    }
}
