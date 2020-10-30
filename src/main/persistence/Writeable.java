package persistence;

import org.json.JSONObject;


// modelled after Writeable class in  JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


// writeable interface
public interface Writeable {

    // EFFECTS: returns this as JSON Object
    JSONObject toJson();

}
