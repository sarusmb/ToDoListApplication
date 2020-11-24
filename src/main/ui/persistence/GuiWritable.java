package ui.persistence;

import org.json.JSONObject;

// writeable interface for gui
public interface GuiWritable {
    // EFFECTS: returns this as JSON Object
    JSONObject toJson();
}
