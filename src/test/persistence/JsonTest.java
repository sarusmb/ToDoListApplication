package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkTask(String description, int id, int dueDate, boolean completed, Task t) {
        assertEquals(description, t.getDescription());
        assertEquals(id, t.getId());
        assertEquals(dueDate, t.getDueDate());
        assertEquals(completed, t.getStatus());
    }
}
