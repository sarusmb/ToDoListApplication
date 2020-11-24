package model;



import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.Iterator;


// represents a to-do list
public class ToDoList implements Writeable {
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

    // REQUIRES : task must already be in the to-do list
    // MODIFIES : this
    // EFFECTS : removes task from the to-do list given its description
    public void removeTask(String description) {
        for (Iterator<Task> iter = allTasks.iterator(); iter.hasNext(); ) {
            Task task = iter.next();
            if (task.getDescription() == description) {
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
            } else {
                pendingTasks.add(null);
            }
        }
        return pendingTasks;
    }


    //getters
    public String getTitle() {
        return title;
    }


    // code modelled after toJson() and thingiesToJason() in WorkRoom Class in JsonSerializationDemo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    // EFFECTS : returns title and all tasks of the To-Do List as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("all tasks", tasksToJson());
        return json;
    }


    // EFFECTS : returns tasks in To-Do list as a JSON array
    private JSONArray tasksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task task: allTasks) {
            jsonArray.put(task.toJson());
        }
        return jsonArray;
    }
}
