package ui.console;

import ui.console.ToDoListApp;

import java.io.FileNotFoundException;


// main method for running ToDoList Application
public class ToDoListConsoleApplication {
    public static void main(String[] args) {
        try {
            new ToDoListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run ToDoList Application, file not found ");
        }
    }
}
