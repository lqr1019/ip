package Siri.Util;

import Siri.Task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> list;
    private int count;

    public TaskManager() {
        list = new ArrayList<>();
        count = 0;
    }

    public List<Task> findTask(String description) {
        List<Task> res = new ArrayList<>();
        for (Task task : list) {
            String des = task.getDescription();
            if (des.contains(description)) {
                res.add(task);
            }
        }
        return res;

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
     */
    public void updateTask(int index, Boolean status) {
        list.get(index).setDone(status);

    }

    /**
     * delete a task from the list
     * @param index the index of the task
     */
    public void deleteTask(int index) {
        list.remove(index);
        count --;
    }


}
