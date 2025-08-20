public class EventTask extends Task{
    private String description;
    private boolean isDone;
    private String from;
    private String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String display() {
        if (isDone) {
            return "[E][X] " + description + " (" + from + to +")";
        } else {
            return "[E][ ] " + description + " (" + from + to +")";
        }
    }
}
