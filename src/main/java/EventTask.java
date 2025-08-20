public class EventTask extends Task{
    private String from;
    private String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String display() {
        if (super.isDone()) {
            return "[E][X] " + super.getDescription() + " (" + from + to +")";
        } else {
            return "[E][ ] " + super.getDescription() + " (" + from + to +")";
        }
    }
}
