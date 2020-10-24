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

    //EFFECTS:
    private void runToDo() {
        System.out.println("Welcome to the To-Do List Application!");
        boolean proceed = true;
        String command = null;

        constructList();

        while (proceed) {
            displayOptions();
            command = scan.next();

            if (command == "q") {
                proceed = false;
            } else {
                processCommand(command);

            }
        }
        System.out.println("You have quit the application. Good-Bye!");
    }

    private void processCommand(String command) {
        if (command.equals("a")) {
            createTask();
        } else if (command.equals("r")) {
            deleteTask();
        } else if (command.equals("c")) {
            taskCompleted();
        } else if (command.equals("v")) {
            viewPendingTasks();
        } else {
            System.out.println("Your input is invalid.");
        }

    }

    // constructs a To-Do list with an empty list
    private void constructList() {
        mylist = new ToDoList();
        Scanner scan = new Scanner(System.in);
    }

    private void displayOptions() {
        System.out.println("Please select one of the following options:");
        System.out.println("a -> add a task");
        System.out.println("r -> remove a task");
        System.out.println("c -> set a task as 'completed'");
        System.out.println("v -> view all pending tasks in to-do list");
        System.out.println("q -> quit the application");
    }


    private void createTask() {
        System.out.println("Enter a description of your task");
        String description = scan.next();
        description += scan.nextLine();

        System.out.println("Enter an ID number");
        int id = scan.nextInt();

        System.out.println("Enter due date in the format 'YYYYMMDD')");
        int dueDate =  scan.nextInt();

        Task task = new Task(description, id, dueDate);

        mylist.allTasks.add(task);
        System.out.println("Task has been added to your to-do list.");

    }

    private void deleteTask() {
        System.out.println("Enter the ID number of the task you wish to remove");
        int removeId = scan.nextInt();

        for (Task task: mylist.allTasks) {
            if (task.getId() == removeId) {
                mylist.allTasks.remove(task);
                System.out.println("Task has been removed to your to-do list.");
            } else {
                System.out.println("Error: Task ID is not found.");
            }
        }
    }

    private void taskCompleted() {
        System.out.println("Enter the ID number of the task you wish set as complete");
        int completeId = scan.nextInt();

        for (Task task: mylist.allTasks) {
            if (task.getId() == completeId) {
                task.changeStatus(true);
            } else {
                System.out.println("Error : Task ID is not found.");
            }
        }

    }

    private void viewPendingTasks() {
        System.out.println("Here is a list of all your pending tasks");
        ArrayList<String> pendingTasks = new ArrayList<>();

        for (Task task: mylist.allTasks) {
            if (task.getStatus() == false) {
                pendingTasks.add(task.getDescription());

                for (String i: pendingTasks) {
                    System.out.println(i);
                }
            }
        }
    }
}




