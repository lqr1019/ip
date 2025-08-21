import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> list;
    private int count;

    public TaskManager() {
        list = new ArrayList<>();
        count = 0;
    }

    /**
     *
     * @return task list
     */
    public List<Task> getTasks() {
        return list;
    }

    /**
     *
     * @return total number of tasks
     */
    public int getCount() {
        return count;
    }

    /**
     * add one task in the list and increase the count by one
     * @param task task added
     */
    public void addTask(Task task) {
        list.add(count, task);
        count ++;
    }

    /**
     * update the status of one task in the list
     * @param index the index of the task in the list
     * @param status the new status of the list
     * @return whether the update is successful
     */
    public boolean updateTask(int index, Boolean status) {
        if (index >= count) {
            return false;
        } else {
            list.get(index).setDone(status);
            return true;
        }
    }


}
