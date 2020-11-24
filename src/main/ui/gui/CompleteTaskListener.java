package ui.gui;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import model.Task;

/* This class has been modelled after FireListener class in the ListDemo Application
https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/
src/components/ListDemo.java
*/


// completes a task when CompleteTaskButton is pressed
public class CompleteTaskListener implements ActionListener {
    private final ApplicationGui applicationGui;
    private String checkMark;

    //EFFECT: creates a completeTaskListener
    public CompleteTaskListener(ApplicationGui applicationGui) {
        this.applicationGui = applicationGui;
        checkMark = "✓";
    }

    @Override
    // REQUIRES: completeTaskButton must be pressed and task must be present in To-Do list
    // MODIFIES: this, Task, ToDoList
    // EFFECTS: sets a task as complete in To-Do List
    public void actionPerformed(ActionEvent e) {
        int index = applicationGui.toDoList.getSelectedIndex();
        String taskDescription = applicationGui.taskModel.getElementAt(index).toString();

        if (!taskDescription.contains(checkMark)) {
            setTaskAsCompleted(taskDescription);
            taskDescription = checkMark + "  " + taskDescription;
            playSound("data/completeTaskSound.wav");
            applicationGui.taskModel.remove(index);
            applicationGui.taskModel.insertElementAt(taskDescription,index);
            applicationGui.toDoList.setSelectedIndex(index);
            applicationGui.toDoList.ensureIndexIsVisible(index);
        } else {
            playSound("data/errorSound.wav");
            return;
        }
    }

    // REQUIRES: task must be in To-Do list
    // MODIFIES: Task, ToDoList
    // EFFECTS : sets a task as complete and adds a checkmark to the description
    public void setTaskAsCompleted(String taskDescription) {
        for (Task task : applicationGui.myTasks.allTasks) {
            if (task.getDescription() == taskDescription) {
                task.editDescription(checkMark + "  " + taskDescription);
                task.changeStatus(true);
            }
        }
    }


    // http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    // REQUIRES: a sound file in data folder
    // EFFECTS : plays a given sound after button is pressed
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
}

