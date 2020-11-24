package ui.gui;

/* This application has been modelled after ListDemo created by Oracle - The Java Tutorials
https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/
src/components/ListDemo.java
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import model.ToDoList;



public class ApplicationGui extends JPanel implements ListSelectionListener {
    protected ToDoList myTasks;
    protected JList toDoList;
    protected DefaultListModel taskModel;
    protected JTextField taskDescription;
    private JScrollPane taskListPane;

    private AddTaskListener addTaskListener;
    private CompleteTaskListener completeTaskListener;
    private JPanel buttonPane;
    private JPanel saveLoadPane;

    private JButton addTaskButton;
    private JButton completeTaskButton;
    protected JButton removeTaskButton;
    private JButton saveButton;
    private JButton loadButton;




    public ApplicationGui() {
        super(new BorderLayout());

        myTasks = new ToDoList();

        taskModel = new DefaultListModel();

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

    public void createSaveLoadPane() {
        saveLoadPane = new JPanel();
        saveLoadPane.setLayout(new BoxLayout(saveLoadPane, BoxLayout.LINE_AXIS));
        saveLoadPane.add(saveButton);
        saveLoadPane.add(loadButton);
        saveLoadPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(saveLoadPane, BorderLayout.PAGE_END);
    }


    public void createTaskJList() {
        toDoList = new JList(taskModel);
        toDoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        toDoList.setSelectedIndex(0);
        toDoList.addListSelectionListener(this);

    }

    public void createTaskListPane() {
        taskListPane = new JScrollPane(toDoList);
        taskListPane.createVerticalScrollBar();
    }

    public void createAddTaskButton() {
        addTaskButton = new JButton("Add Task");
        addTaskListener = new AddTaskListener(this, addTaskButton);
        addTaskButton.setActionCommand("Add Task");
        addTaskButton.addActionListener(addTaskListener);
        addTaskButton.setEnabled(false);
    }


    public void createRemoveTaskButton() {
        removeTaskButton = new JButton("Remove Task");
        RemoveTaskListener removeTaskListener = new RemoveTaskListener(this);
        removeTaskButton.setActionCommand("Remove Task");
        removeTaskButton.addActionListener(removeTaskListener);
    }

    public void createCompleteTaskButton() {
        completeTaskButton = new JButton("Complete Task");
        completeTaskListener = new CompleteTaskListener(this);
        completeTaskButton.setActionCommand("Remove Task");
        completeTaskButton.addActionListener(completeTaskListener);
        completeTaskButton.setEnabled(true);
    }

    public void createSaveButton() {
        saveButton = new JButton("Save");
    }

    public void createLoadButton() {
        loadButton = new JButton("Load");
    }


    public void createDescriptionField() {
        taskDescription = new JTextField(5);
        taskDescription.addActionListener(addTaskListener);
        taskDescription.getDocument().addDocumentListener(addTaskListener);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (toDoList.getSelectedIndex() == -1) {
                removeTaskButton.setEnabled(false);

            } else {
                removeTaskButton.setEnabled(true);
            }
        }
    }


}
