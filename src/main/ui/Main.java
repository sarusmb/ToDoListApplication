package ui;

import java.io.FileNotFoundException;

// main method for running ToDoList Application
public class Main {
    public static void main(String[] args) {
        try {
            new ToDoListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run ToDoList Application, file not found ");
        }
    }
}
