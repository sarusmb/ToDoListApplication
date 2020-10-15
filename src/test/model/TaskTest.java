package model;

import model.Task;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


class TaskTest {
    private Task testTask;

    @BeforeEach

    public void setup() {
        testTask = new Task("Go to mall", 1, 20201220);
    }

    @Test
    public void testInitialTask() {
        // tests that a task is initialized correctly
        assertEquals(testTask.getName(), "Go to mall");
        assertEquals(testTask.getId(), 1);
        assertEquals(testTask.getDueDate(), 20201220);
        assertFalse(testTask.getStatus());
    }

    @Test
    public void testChangeTaskDescription() {
        // tests that a task's description can be changed
        testTask.editDescription("Go to school");
        assertEquals(testTask.getName(), "Go to school");
    }

    @Test
    public void testChangeDueDate() {
        // tests that a task's due date can be changed
        testTask.editDueDate(20201112);
        assertEquals(testTask.getDueDate(),20201112);

    }

    @Test
    public void testChangeStatus() {
        // tests that a task's status can be changed
        testTask.changeStatus(true);
        assertTrue(testTask.getStatus());
    }

}

