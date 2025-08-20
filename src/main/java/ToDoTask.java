public class ToDoTask extends Task{


    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String display() {
        if (super.isDone()) {
            return "[T][X] " + super.getDescription();
        } else {
            return "[T][ ] " + super.getDescription();
        }
    }
}
