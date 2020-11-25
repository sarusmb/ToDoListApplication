package ui.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import model.Task;


/* This class has been modelled after HireListener class in the ListDemo Application
https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/
src/components/ListDemo.java
*/

// adds a task to the To-Do List when AddTaskButton is pressed
class AddTaskListener implements ActionListener, DocumentListener {
    private ApplicationGui applicationGui;
    private boolean buttonEnabled = false;
    private JButton button;


    // EFFECTS : creates an addTaskListener
    public AddTaskListener(ApplicationGui applicationGui, JButton clickedAddButton) {
        this.applicationGui = applicationGui;
        this.button = clickedAddButton;
    }

    @Override
    // REQUIRES: addTaskButton must be pressed and task description bar is not empty
    // MODIFIES: this, Task, ToDoList
    // EFFECTS : adds a task to a To-Do list
    public void actionPerformed(ActionEvent e) {
        String description = applicationGui.taskDescription.getText();
        int index = applicationGui.toDoList.getSelectedIndex();
        if (index == -1) {
            index = 0;
        } else {
            index++;
        }
        Task task = new Task(description, 0,00000000);
        applicationGui.myTasks.addTask(task);
        applicationGui.taskModel.insertElementAt(description, index);
        playSound("data/clickSound.wav");

        applicationGui.taskDescription.requestFocusInWindow();
        applicationGui.taskDescription.setText("");
        applicationGui.toDoList.setSelectedIndex(index);
        applicationGui.toDoList.ensureIndexIsVisible(index);
    }

    //getter
    public ApplicationGui getApplicationGui() {
        return applicationGui;
    }

    //setter
    public void setApplicationGui(ApplicationGui appGui) {
        if (getApplicationGui() != appGui) {
            this.applicationGui = appGui;
            applicationGui.addListenerToAddButton(this);
        }
    }


    // http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    // REQUIRES: a sound file in data folder
    // EFFECTS : plays a given sound
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    @Override
    // from ListDemo
    // MODIFIES: this
    // EFFECTS: given a document event, enableButton function is called
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }


    @Override
    // from ListDemo
    // MODIFIES: this
    // EFFECTS: when portion of document is removed, handleEmptyTextField() is called
    public void removeUpdate(DocumentEvent e) {
        handleEmptyTextField(e);
    }


    @Override
    // from ListDemo
    // MODIFIES: this
    // EFFECTS: given a document event, if  handleEmptyTextField() is false, then enableButton() is called
    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }

    }

    // from ListDemo
    // MODIFIES: this
    // EFFECTS: if button is not enabled, set it as enabled
    private void enableButton() {
        if (!buttonEnabled) {
            button.setEnabled(true);
        }
    }

    // from ListDemo
    // MODIFIES: this
    // EFFECTS: when portion of document is removed and length is less than 0, the buttons
    // are disabled and returns true
    // otherwise returns false
    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            button.setEnabled(false);
            buttonEnabled = false;
            return true;
        }
        return false;
    }
}
