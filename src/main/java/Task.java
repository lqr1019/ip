public class Task {
    public String description;
    public boolean isDone;

    public Task(){
        isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String display() {
        if (isDone) {
            return "[X]" + description;
        } else {
            return "[ ]" + description;
        }
    }

}
