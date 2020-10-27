package ui;

import model.Task;
import model.ToDoList;

import java.util.ArrayList;
import java.util.Scanner;


// Code format and implementation of Java Scanner has been followed from CPSC 210-TellerApp
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

// To-Do list application
public class ToDoListApp {
    private ToDoList mylist;
    private Scanner scan;

    // EFFECTS : Runs the To-Do list application
    public ToDoListApp() {
        runToDo();
    }

    // MODIFIES: this
    // EFFECTS: processes the user's inputs
    private void runToDo() {
        System.out.println("Welcome to the To-Do List Application!\n");
        boolean proceed = true;
        scan = new Scanner(System.in);
        String command = null;

        constructList();

        while (proceed) {
            displayOptions();
            command = scan.next();

            if (command.equals("q")) {
                proceed = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("You have quit the application. Good-Bye!");
    }

    // MODIFIES: this
    // EFFECTS: processes the commands of a user
    private void processCommand(String command) {
        if (command.equals("a")) {
            createTask();
        } else if (command.equals("r")) {
            deleteTask();
        } else if (command.equals("c")) {
            taskCompleted();
        } else if (command.equals("v")) {
            viewPendingTasks();
        }  else if (command.equals("q")) {
            System.out.println("You have quit the application. Good-Bye!");
            System.exit(1);
        } else {
            System.out.println("Your input is invalid.");
        }

    }


    // MODIFIES: this
    // EFFECTS: initializes a To-Do list
    private void constructList() {
        mylist = new ToDoList();
        Scanner scan = new Scanner(System.in);
    }

    // EFFECTS: displays a menu of commands that a user can input
    private void displayOptions() {
        System.out.println("Please select one of the following options:");
        System.out.println("a -> add a task");
        System.out.println("r -> remove a task");
        System.out.println("c -> set a task as 'completed'");
        System.out.println("v -> view all pending tasks in to-do list");
        System.out.println("q -> quit the application\n");
    }

    // MODIFIES: this, ToDoList
    // EFFECTS:  based on the user's input, it creates a task and inputs it into the To-do list
    private void createTask() {
        System.out.println("Enter a description of your task");
        String description = scan.next();
        description += scan.nextLine();

        System.out.println("Enter an ID number");
        int id = scan.nextInt();

        System.out.println("Enter due date in the format 'YYYYMMDD')");
        int dueDate = scan.nextInt();
        Task task = new Task(description, id, dueDate);
        mylist.addTask(task);
        System.out.println("Task has been added to your to-do list.");

    }


    // REQUIRES: To-do list is not empty
    // MODIFIES: this, ToDoList
    // EFFECTS: given an ID number, corresponding task will be removed from list
    private void deleteTask() {
        System.out.println("Enter the ID number of the task you wish to remove");
        int removeId = scan.nextInt();
        mylist.removeTask(removeId);
        System.out.println("Task has been removed to your to-do list.");

//        for (Iterator<Task> iter = mylist.allTasks.iterator(); iter.hasNext(); ) {
//            Task task = iter.next();
//            if (task.getId() == removeId) {
//                iter.remove();
//                System.out.println("Task has been removed to your to-do list.");
//            }
//        }
    }

    // REQUIRES: a task present in To-Do list
    // MODIFIES: this, ToDoList
    // EFFECTS: given the ID number, it sets the task as completed
    private void taskCompleted() {
        System.out.println("Enter the ID number of the task you wish set as complete");
        int completeId = scan.nextInt();

        for (Task task : mylist.allTasks) {
            if (task.getId() == completeId) {
                task.changeStatus(true);
                System.out.println("This task is completed.\n");
            }
        }
    }


    // EFFECTS: prints out all pending tasks
    private void viewPendingTasks() {
        System.out.println("Here is a list of all your pending tasks");
        ArrayList<String> pendingTasks = new ArrayList<>();

        for (Task task : mylist.allTasks) {
            if (task.getStatus() == false) {
                System.out.println(task.getDescription() + "\n");
            }
        }
    }
}






