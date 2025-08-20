import java.util.ArrayList;
import java.util.List;

public class TaskManger {
    public List<Task> list;
    public int count;

    public TaskManger() {
        list = new ArrayList<>();
        count = 0;
    }

    public List<Task> getTasks() {
        return list;
    }

    public void addTask(Task task) {
        list.add(count, task);
    }

    public boolean updateTask(int index, Boolean status) {
        if (index >= count) {
            return false;
        } else {
            list.get(index).setDone(status);
            return true;
        }
    }

    public void displayList() {
        System.out.print("____________________________________________________________\n");
        System.out.print("Here are the tasks in your list:\n");
        for (int i = 0; i < count; i ++) {
            System.out.print(i + 1 + list.get(i).display() +"\n");
        }
        System.out.print("____________________________________________________________\n");
    }
}
