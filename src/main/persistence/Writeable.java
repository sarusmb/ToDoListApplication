package persistence;

import org.json.JSONObject;

// writeable interface
public interface Writeable {

    // EFFECTS: returns this as JSON Object
    JSONObject toJson();

}
