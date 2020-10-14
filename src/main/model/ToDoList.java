package model;

import model.Task;

import java.util.ArrayList;
import java.util.List;

// represents a to-do list
public class ToDoList {
    private static final String title = "My TO-DO List";
    public ArrayList<Task> allTasks;

    public ToDoList() {

    }

    // MODIFIES: this
    // EFFECTS : adds task to the to-do list
    public void addTask(Task t) {
        //stub
    }

    // REQUIRES : task must already be in the to-do list
    // MODIFIES: this
    // EFFECTS : removes task from the to-do list given its ID number
    public void removeTask(Task t) {
        //stub
    }


    // EFFECTS : returns all the remaining tasks in the to-do list
    public List remainingTasks() {
        return allTasks;

    }

}
