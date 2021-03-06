package model;


import org.json.JSONObject;
import persistence.Writeable;

// represents a task
public class Task implements Writeable {
    private String description;
    private int id;
    private int dueDate;
    private boolean completed;

    // REQUIRES: description must be >=1 character and <= 100 characters. ID number must by >=0
    //           and <= 999. ID Must be an integer value.
    // EFFECTS: creates a task with a description, id and due date,
    public Task(String description, int id, int dueDate)  {
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
    public String getDescription() {
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

    // code modelled after toJson() in JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    @Override
    // EFFECTS : returns task as json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("description", description);
        json.put("task iD", id);
        json.put("due date (YYYYMMDD)", dueDate);
        json.put("task completed?", completed);
        return json;
    }
}
