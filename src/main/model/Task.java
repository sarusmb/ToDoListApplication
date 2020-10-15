package model;


// represents a task
public class Task {
    private String description;
    private int id;
    private int dueDate;
    private boolean completed;


    // EFFECTS: creates a task with a description, due date, any additional notes and is yet to be completed
    public Task(String description, int id, int dueDate) {
        this.description = description;
        this.id = id;
        this.dueDate = dueDate;
        completed = false;
    }

    // REQUIRES: task already has a description
    // MODIFIES: this
    // EFFECTS: changes the description of a task
    public void editDescription(String rename) {
        this.description = rename;
    }

    // REQUIRES : task already has a date
    // MODIFIES: this
    // EFFECTS : changes the due date of a task
    public void editDueDate(int newDate) {
        dueDate = newDate;

    }

    // MODIFIES : this
    // EFFECTS : changes the status to true if task is completed, or false if task is still pending
    public void changeStatus(boolean completed) {
        this.completed = completed;
    }

    //getters
    public String getName() {
        return this.description;
    }

    public int getId() {
        return id;
    }

    public int getDueDate() {
        return dueDate;
    }

    public boolean getStatus() {
        return completed;
    }

}
