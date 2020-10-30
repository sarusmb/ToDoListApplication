package persistence;

import model.Task;

import model.ToDoList;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// modelled after JsonReader class in JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads a To-Do List from JSON data stored in a saved file
public class JsonReader {
    private String source;


    // EFFECTS: constructs a reader to read the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS : reads a To-Do List from source file and returns it
    // throws an IOException if reading data from file produces error
    public ToDoList read() throws IOException {

        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS : reads source file as String and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS : parses To-Do List from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        String name = jsonObject.getString("title");
        ToDoList td = new ToDoList();
        addTasks(td, jsonObject);
        return td;
    }


    // MODIFIES: td
    // EFFECTS: parses tasks from JSON object and adds them to the To-Do list
    private void addTasks(ToDoList td, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("all tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(td,nextTask);
        }
    }

    // MODIFIES: td
    // EFFECTS : parses all tasks from JSON object and adds it to To-Do list
    private void addTask(ToDoList td, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        int id = jsonObject.getInt("task iD");
        int dueDate = jsonObject.getInt("due date (YYYYMMDD)");
        Task task = new Task(description, id, dueDate);
        td.addTask(task);
    }
}
