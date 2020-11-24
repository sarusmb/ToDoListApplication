package ui.gui;

/* This application has been modelled after ListDemo created by Oracle - The Java Tutorials
https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/
src/components/ListDemo.java
 */

import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import ui.persistence.GuiReader;
import ui.persistence.GuiWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;



public class ApplicationGui extends JPanel implements ListSelectionListener {
    protected JList tasks;
    protected DefaultListModel taskModel;
    protected JTextField taskDescription;
    private JScrollPane taskListPane;
    private JButton addTaskButton;

    protected JButton removeTaskButton;
    private AddTaskListener addTaskListener;
    private JPanel buttonPane;
    private JButton completeTaskButton;
    private CompleteTaskListener completeTaskListener;

    private GuiReader jsonReader;



    private JButton saveButton;
    private SaveListener saveListener;

    public ApplicationGui() {
        super(new BorderLayout());
        taskModel = new DefaultListModel();
        taskModel.addElement("");
        createTaskJList();
        createTaskListPane();
        createAddTaskButton();
        createRemoveTaskButton();
        createCompleteTaskButton();
        createSaveButton();
        createDescriptionField();
        createButtonPane();
        createToDoListFrame();


    }



    public void createToDoListFrame() {
        JFrame frame = new JFrame();
        frame.setSize(700,400);
        frame.setTitle("To-Do List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(buttonPane, BorderLayout.PAGE_START);
        frame.add(taskListPane, BorderLayout.CENTER);
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

    public void createTaskJList() {
        tasks = new JList(taskModel);
        tasks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tasks.setSelectedIndex(0);
        tasks.addListSelectionListener(this);

    }

    public void createTaskListPane() {
        taskListPane = new JScrollPane(tasks);
        taskListPane.createVerticalScrollBar();
    }

    public void createAddTaskButton() {
        addTaskButton = new JButton("Add Task");
        addTaskListener = new AddTaskListener(this, addTaskButton);
        addTaskButton.setActionCommand("Add Task");
        addTaskButton.addActionListener(addTaskListener);
        addTaskButton.setEnabled(false);
    }

    public void createSaveButton() {
        saveButton = new JButton("Save");
        saveListener = new saveListener(this, saveButton);
        addTaskButton.setActionCommand("saveListener");
        addTaskButton.addActionListener(addTaskListener);

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


    public void createDescriptionField() {
        taskDescription = new JTextField(5);
        taskDescription.addActionListener(addTaskListener);
        taskDescription.getDocument().addDocumentListener(addTaskListener);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (tasks.getSelectedIndex() == -1) {
                removeTaskButton.setEnabled(false);

            } else {
                removeTaskButton.setEnabled(true);
            }
        }
    }

    //----------JSON reader & writer

    // EFFECTS : returns tasks in To-Do list as a JSON array
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < taskModel.size(); i++) {
            String task = taskModel.getElementAt(i).toString();
            jsonArray.put(task);
        }
        return jsonArray;
    }




   /* // MODIFIES : this
    // EFFECTS : loads To-Do list from a source file
    private void loadToDoList() {
        try {
            Athis = jsonReader.read();
        } catch (IOException e) {
            //expected
        }
    }*/

}
