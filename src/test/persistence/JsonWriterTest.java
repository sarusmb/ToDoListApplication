/*
package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// tests for JsonWriter
// modelled based on JsonSerializationDemo
public class JsonWriterTest extends JsonTest {
    private ToDoList td;
    private Task t1 = new Task("Buy groceries", 1, 20201017);
    private Task t2 = new Task("Go jogging", 2, 20201018);
    private Task t3 = new Task("Go to Gym", 3 ,20201014);
    private Task t4 = new Task ("Do math assignment", 4, 20201015);

    @BeforeEach
    public void setup() {
         td = new ToDoList();
    }

    @Test
    public void testWriterInvalidFile() {
        JsonWriter writer = new JsonWriter("./data/\0illegalFileName.json");
        try {
            writer.open();
            fail("FileNotFoundException expected");
        } catch (FileNotFoundException e) {
            // expected
        }
    }

    @Test
    public void testWriterEmptyToDoList() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
            td = reader.read();
            assertEquals("My To-Do List", td.getTitle());
            assertEquals(0, td.allTasks.size());
        } catch (IOException e) {
            fail("IO Exception caught");
        }
    }

    @Test
    public void testWriterToDoList() {
        addTasksOneToFour();
        JsonWriter writer = new JsonWriter("./data/testWriterToDoList.json");
        JsonReader reader = new JsonReader("./data/testWriterToDoList.json");

        try {
            writer.open();
            writer.write(td);
            writer.close();
            td = reader.read();

            checkTask("Buy groceries",1,20201017, false, td.allTasks.get(0));
            checkTask("Go jogging",2,20201018, false, td.allTasks.get(1));
            checkTask("Go to Gym",3,20201014, false, td.allTasks.get(2));
            checkTask("Do math assignment",4,20201015, false, td.allTasks.get(3));
            assertEquals("My To-Do List", td.getTitle());
            assertEquals(4, td.allTasks.size());
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException thrown, not expected");
        } catch (IOException e) {
            fail("IOException thrown, not expected");
        }
    }

    public void addTasksOneToFour() {
        td.addTask(t1);
        td.addTask(t2);
        td.addTask(t3);
        td.addTask(t4);
    }
}








*/
