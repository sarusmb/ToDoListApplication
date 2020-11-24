package ui.persistence;


import org.json.JSONArray;
import ui.gui.ApplicationGui;

import javax.swing.*;
import java.io.*;

// code modelled after JsonWriter Class in JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class GuiWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS : constructs a writer that writes the Json representation of ToDOList to a destination file
    public GuiWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES : this
    // EFFECTS : if destination file is not found, throws FileNotFoundException
    //              otherwise, writer is opened
    public void open() throws FileNotFoundException {
        File fileDestination = new File(destination);

        if (fileDestination.exists()) {
            writer = new PrintWriter(fileDestination);
        } else {
            throw new FileNotFoundException();
        }
    }

    // MODIFIES: this
    // EFFECTS : writes a Json representation of a JList to destination file provided
    public void write(DefaultListModel taskModel) {
        JSONArray json = taskModel.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes a string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
