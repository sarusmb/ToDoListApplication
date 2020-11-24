package ui.gui;

import com.sun.webkit.dom.HTMLDocumentImpl;
import org.json.JSONArray;
import ui.persistence.GuiWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveListener implements ActionListener {
    private final ApplicationGui applicationGui;
    private static final String JSON_STORE = "./data/toDoListManager.json";
    private GuiWriter jsonWriter;


    public SaveListener(ApplicationGui applicationGui) {
        this.applicationGui = applicationGui;
        jsonWriter = new GuiWriter();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toJson();
        saveToDoList();
    }

    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < applicationGui.taskModel.size(); i++) {
            String task = applicationGui.taskModel.getElementAt(i).toString();
            jsonArray.put(task);
        }
        return jsonArray;
    }



    // EFFECTS : saves To-Do list to a destination file
    private void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(applicationGui.taskModel);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound: Unable to write your To-Do list to " + JSON_STORE);
        }
    }
}
