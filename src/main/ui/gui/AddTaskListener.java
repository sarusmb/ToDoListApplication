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

class AddTaskListener implements ActionListener, DocumentListener {
    private final ApplicationGui applicationGui;
    private boolean buttonEnabled = false;
    private JButton button;

    public AddTaskListener(ApplicationGui applicationGui, JButton clickedAddButton) {
        this.applicationGui = applicationGui;
        this.button = clickedAddButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String description = applicationGui.taskDescription.getText();
        int index = applicationGui.toDoList.getSelectedIndex();
        if (index == -1) {
            index = 0;
        } else {
            index++;
        }
        applicationGui.taskModel.insertElementAt(description, index);
        playSound("data/clickSound.wav");

        Task task = new Task(description, 0,0);
        applicationGui.myTasks.addTask(task);

        applicationGui.taskDescription.requestFocusInWindow();
        applicationGui.taskDescription.setText("");
        applicationGui.toDoList.setSelectedIndex(index);
        applicationGui.toDoList.ensureIndexIsVisible(index);

    }


    //http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
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
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }


    @Override
    public void removeUpdate(DocumentEvent e) {
        handleEmptyTextField(e);
    }


    @Override
    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }

    }

    private void enableButton() {
        if (!buttonEnabled) {
            button.setEnabled(true);
        }
    }

    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            button.setEnabled(false);
            buttonEnabled = false;
            return true;
        }
        return false;
    }



}
