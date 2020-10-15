package model;

import static org.junit.jupiter.api.Assertions.*;
import model.ToDoList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class ToDoListTest {
    private ToDoList testToDoList;
    Task t1 = new Task("Go to Gym", 1 ,20201014);
    Task t2 = new Task ("Do math assignment", 2, 20201015);
    Task t3 = new Task("Make dinner", 3, 20201014);
    Task t4 = new Task("Submit proposal", 4, 20201110);
    Task t5 = new Task("Buy groceries", 5, 20201017);
    Task t6 = new Task("Go jogging", 6, 20201018);

    @BeforeEach
    public void setup() {
        testToDoList = new ToDoList();
    }

    @Test
    public void testInitialToDoList() {
        assertEquals(testToDoList.getTitle() , "My To-Do List");
        assertEquals(testToDoList.remainingTasks(), testToDoList.allTasks);
    }

    @Test
    public void testAddTask() {
        testToDoList.addTask(t1);
        testToDoList.addTask(t2);
        testToDoList.addTask(t3);
        testToDoList.addTask(t4);

        assertEquals(testToDoList.allTasks.size(),4);
        assertTrue(testToDoList.allTasks.contains(t1));
        assertTrue(testToDoList.allTasks.contains(t2));
        assertTrue(testToDoList.allTasks.contains(t3));
        assertTrue(testToDoList.allTasks.contains(t4));
    }

    @Test
    public void testRemoveTask() {
        testToDoList.addTask(t1);
        testToDoList.addTask(t2);
        testToDoList.addTask(t3);
        testToDoList.addTask(t4);
        testToDoList.removeTask(t3);
        testToDoList.removeTask(t4);

        assertEquals(testToDoList.allTasks.size(),2);
        assertTrue(testToDoList.allTasks.contains(t1));
        assertTrue(testToDoList.allTasks.contains(t2));
        assertFalse(testToDoList.allTasks.contains(t3));
        assertFalse(testToDoList.allTasks.contains(t4));
    }

   @Test
   public void testReturnRemainingTasks() {
       testToDoList.addTask(t1);
       testToDoList.addTask(t2);
       testToDoList.addTask(t4);

       assertEquals(testToDoList.remainingTasks().size(),3);
       assertTrue(testToDoList.remainingTasks().contains(t1));
       assertTrue(testToDoList.remainingTasks().contains(t2));
       assertTrue(testToDoList.remainingTasks().contains(t4));
   }
}