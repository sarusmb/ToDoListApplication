package ui.gui;

/* This application has been modelled after ListDemo created by Oracle - The Java Tutorials
https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/
src/components/ListDemo.java
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

// To-Do List Manager Application
public class ApplicationGui extends JPanel implements ListSelectionListener {
    protected ToDoList myTasks;
    protected JList toDoList;
    protected DefaultListModel taskModel;


    private AddTaskListener addTaskListener;
    private CompleteTaskListener completeTaskListener;

    private JPanel buttonPane;
    private JPanel saveLoadPane;
    private JButton addTaskButton;
    private JButton completeTaskButton;
    protected JButton removeTaskButton;
    private JButton saveButton;
    private JButton loadButton;
    protected JTextField taskDescription;
    private JScrollPane taskListPane;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/toDoListManager.json";

    // EFFECTS: constructs To-Do List application gui
    public ApplicationGui() {
        super(new BorderLayout());
        myTasks = new ToDoList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        taskModel = new DefaultListModel();
        taskModel.addElement("");
        createTaskJList();
        createTaskListPane();
        createAddTaskButton();
        createRemoveTaskButton();
        createCompleteTaskButton();
        createSaveButton();
        createLoadButton();
        createDescriptionField();
        createButtonPane();
        createSaveLoadPane();
        createToDoListFrame();
    }

    // REQUIRES: buttonPane, taskListPane, saveLoadPane must be constructed
    // MODIFIES: this
    // EFFECTS: creates the To-Do list frame
    public void createToDoListFrame() {
        JFrame frame = new JFrame();
        frame.setSize(700,400);
        frame.setTitle("To-Do List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(buttonPane, BorderLayout.PAGE_START);
        frame.add(taskListPane, BorderLayout.CENTER);
        frame.add(saveLoadPane, BorderLayout.PAGE_END);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    // REQUIRES: addTaskButton, removeTaskButton, completeTaskButton,taskDescription must be constructed
    // MODIFIES: this
    // EFFECTS: creates button panel
    public void createButtonPane() {
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(removeTaskButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(completeTaskButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(taskDescription);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(addTaskButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        add(buttonPane, BorderLayout.PAGE_START);

    }

    // REQUIRES: saveButton, loadButton must be constructed
    // MODIFIES: this
    // EFFECTS: creates saveLoadPanel
    public void createSaveLoadPane() {
        saveLoadPane = new JPanel();
        saveLoadPane.setLayout(new BoxLayout(saveLoadPane, BoxLayout.LINE_AXIS));
        saveLoadPane.add(saveButton);
        saveLoadPane.add(loadButton);
        saveLoadPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(saveLoadPane, BorderLayout.PAGE_END);
    }


    // REQUIRES: saveButton, loadButton must be constructed
    // MODIFIES: this
    // EFFECTS: creates saveLoadPanel
    public void createTaskJList() {
        toDoList = new JList(taskModel);
        toDoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        toDoList.setSelectedIndex(0);
        toDoList.addListSelectionListener(this);

    }

    // REQUIRES: saveButton, loadButton must be constructed
    // MODIFIES: this
    // EFFECTS: creates saveLoadPanel
    public void createTaskListPane() {
        taskListPane = new JScrollPane(toDoList);
        taskListPane.createVerticalScrollBar();
    }

    // MODIFIES: this
    // EFFECTS: creates addTaskButton
    public void createAddTaskButton() {
        addTaskButton = new JButton("Add Task");
        addTaskListener = new AddTaskListener(this, addTaskButton);
        addTaskButton.setActionCommand("Add Task");
        addTaskButton.addActionListener(addTaskListener);
        addTaskButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: creates removeTaskButton
    public void createRemoveTaskButton() {
        removeTaskButton = new JButton("Remove Task");
        RemoveTaskListener removeTaskListener = new RemoveTaskListener(this);
        removeTaskButton.setActionCommand("Remove Task");
        removeTaskButton.addActionListener(removeTaskListener);
    }

    // MODIFIES: this
    // EFFECTS: creates completeTaskButton
    public void createCompleteTaskButton() {
        completeTaskButton = new JButton("Complete Task");
        completeTaskListener = new CompleteTaskListener(this);
        completeTaskButton.setActionCommand("Remove Task");
        completeTaskButton.addActionListener(completeTaskListener);
        completeTaskButton.setEnabled(true);
    }



    // EFFECTS: creates saveButton
    public void createSaveButton() {
        saveButton = new JButton("Save");
        SaveListener saveListener = new SaveListener(this);
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(true);
    }


    // EFFECTS: creates loadButton
    public void createLoadButton() {
        loadButton = new JButton("Load");
        LoadListener loadListener = new LoadListener(this);
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(loadListener);
        loadButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: creates descriptionField
    public void createDescriptionField() {
        taskDescription = new JTextField(5);
        taskDescription.addActionListener(addTaskListener);
        taskDescription.getDocument().addDocumentListener(addTaskListener);
    }

    @Override
    // from ListDemo
    // EFFECTS: if  ListSelectionEvent's value is not adjusting and selected task has index -1, set
    // disable removeTaskButton & completeTaskButton, else enable both buttons
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (toDoList.getSelectedIndex() == -1) {
                removeTaskButton.setEnabled(false);
                completeTaskButton.setEnabled(false);

            } else {
                removeTaskButton.setEnabled(true);
                completeTaskButton.setEnabled(true);
            }
        }
    }


    // EFFECTS : saves To-Do list to a destination file
    public void saveToDoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(myTasks);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound: Unable to write your To-Do list to " + JSON_STORE);
        }
    }


    // MODIFIES : this
    // EFFECTS : loads To-Do list from a source file
    public void loadToDoList() {
        try {
            myTasks = jsonReader.read();
            for (Task task : myTasks.allTasks) {
                String taskDescription = task.getDescription();
                taskModel.addElement(taskDescription);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
