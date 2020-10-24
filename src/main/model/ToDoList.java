package model;

import model.Task;

import java.util.ArrayList;
import java.util.List;

// represents a to-do list
public class ToDoList {
    public String title;
    public ArrayList<Task> allTasks;

// makes a to-do list
    public ToDoList() {
        title = "My To-Do List";
        allTasks = new ArrayList<Task>();
    }

    // MODIFIES: this
    // EFFECTS : adds task to the to-do list
    public void addTask(Task t) {
        allTasks.add(t);
    }

    // REQUIRES : task must already be in the to-do list
    // MODIFIES: this
    // EFFECTS : removes task from the to-do list given its ID number
    public void removeTask(Task t) {
        allTasks.remove(t);
    }

    // EFFECTS : returns all the remaining tasks in the to-do list
    public List remainingTasks() {
        return allTasks;

    }

    //getters

    public String getTitle() {
        return title;
    }



}
