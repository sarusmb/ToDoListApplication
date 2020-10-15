package ui;

import model.*;

public class Main {
    public static void main(String[] args) {
        Task t1 = new Task("Go to Gym", 1 ,20201014);
        Task t2 = new Task ("Do math assignment", 2, 20201015);
        Task t3 = new Task("Make dinner", 3, 20201014);
        Task t4 = new Task("Submit proposal", 4, 20201110);
        Task t5 = new Task("Buy groceries", 5, 20201017);
        Task t6 = new Task("Go jogging", 6, 20201018);

        t1.editDescription("Go for a walk");
        t2.editDueDate(20201020);
        t3.changeStatus(true);
        t4.changeStatus(true);

        ToDoList mylist = new ToDoList();
        mylist.allTasks.add(t1);
        mylist.allTasks.add(t2);
        mylist.allTasks.add(t3);
        mylist.allTasks.add(t4);
        mylist.allTasks.add(t5);
        mylist.allTasks.add(t6);

        mylist.allTasks.remove(t6);

        mylist.remainingTasks();

    }

}
