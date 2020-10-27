package model;

import model.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// represents a to-do list
public class ToDoList {
    public String title;
    public ArrayList<Task> allTasks;


// EFFECTS: initializes a to-do list
    public ToDoList() {
        title = "My To-Do List";
        allTasks = new ArrayList<Task>();
    }

    // MODIFIES: this
    // EFFECTS : adds task to the to-do list
    public void addTask(Task t) {
        allTasks.add(t);
    }

    // Source for  implementation of iterator:
    // https://stackoverflow.com/questions/15384486/java-concurrent-modification-exception-error
    // REQUIRES : task must already be in the to-do list
    // MODIFIES: this
    // EFFECTS : removes task from the to-do list given its ID number
    public void removeTask(int id) {
        for (Iterator<Task> iter = allTasks.iterator(); iter.hasNext(); ) {
            Task task = iter.next();
            if (task.getId() == id) {
                iter.remove();
            }
        }
    }

    // EFFECTS : constructs a list of remaining tasks
    public ArrayList remainingTasks() {
        ArrayList<Task> pendingTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (!task.getStatus()) {
                pendingTasks.add(task);
            }
        }
        return pendingTasks;
    }

    //getters

    public String getTitle() {
        return title;
    }
}
