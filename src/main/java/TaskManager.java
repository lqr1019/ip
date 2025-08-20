import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> list;
    private int count;

    public TaskManager() {
        list = new ArrayList<>();
        count = 0;
    }

    public List<Task> getTasks() {
        return list;
    }

    public int getCount() {
        return count;
    }

    public void addTask(Task task) {
        list.add(count, task);
        count ++;
    }

    public boolean updateTask(int index, Boolean status) {
        if (index >= count) {
            return false;
        } else {
            list.get(index).setDone(status);
            return true;
        }
    }


}
