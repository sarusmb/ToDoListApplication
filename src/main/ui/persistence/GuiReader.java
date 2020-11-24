package ui.persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


import model.ToDoList;
import org.json.*;
import ui.gui.ApplicationGui;

import javax.swing.*;

// modelled after JsonReader class in JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads a To-Do List from JSON data stored in a saved file
public class GuiReader {
    private String source;

    // EFFECTS: constructs a reader to read the source file
    public GuiReader(String source) {
        this.source = source;
    }

    // EFFECTS : reads a To-Do List from source file and returns it
    // throws an IOException if reading data from file produces error
    public ApplicationGui read() throws IOException {

        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseToDoList(jsonArray);
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
    private ApplicationGui parseToDoList(JSONArray jsonArray) {
        ApplicationGui applicationGui = new ApplicationGui();
        addTasks(applicationGui, jsonArray);
        return applicationGui;
    }

    // MODIFIES: td
    // EFFECTS: parses tasks from JSON object and adds them to the To-Do list
    private void addTasks(ApplicationGui applicationGui, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            DefaultListModel taskModel = new DefaultListModel();

            taskModel.addElement(json);
        }
    }











}
