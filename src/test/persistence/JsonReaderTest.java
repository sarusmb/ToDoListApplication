package persistence;


import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test for JsonReader
// modelled based on JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNoSuchFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile()");
        try {
            ToDoList td = reader.read();
            fail("No Exception thrown, IOException expected");
        } catch (IOException e) {
           // expected
        }
    }

    @Test
    public void testReaderEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList");

        try {
            ToDoList td = reader.read();
            assertEquals("My To-Do List", td.getTitle());
            assertEquals(0, td.allTasks.size());
        } catch (IOException e) {
            fail("IO Exception thrown, not expected");
        }
    }

    @Test
    public void testReaderToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderToDoList");
        try {
            ToDoList td = reader.read();
            assertEquals("My To-Do List", td.getTitle());
            assertEquals(4, td.allTasks.size());

            checkTask("Do math assignment",4,20201015, false, td.allTasks.get(0));
            checkTask("Go to Gym",3,20201014, false, td.allTasks.get(1));
            checkTask("Go jogging",2,20201018, false, td.allTasks.get(2));
            checkTask("Buy groceries",1,20201017, false, td.allTasks.get(3));

        } catch (IOException e) {
            fail("IO Exception thrown, not expected");
        }
    }
















}
