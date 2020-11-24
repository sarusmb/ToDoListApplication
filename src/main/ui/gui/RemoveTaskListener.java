package ui.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import model.Task;


/* This class has been modelled after FireListener class in the ListDemo Application
https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/
src/components/ListDemo.java
*/

// removes a task when removeTaskButton is pressed
public class RemoveTaskListener implements ActionListener {
    private final ApplicationGui applicationGui;

    // EFFECT : creates a removeTaskListener
    public RemoveTaskListener(ApplicationGui applicationGui) {
        this.applicationGui = applicationGui;
    }

    @Override
    // REQUIRES: removeTaskButton must be pressed and task must be present in To-Do list
    // MODIFIES: this, Task, ToDoList
    // EFFECTS : removes a task from To-Do list
    public void actionPerformed(ActionEvent e) {
        int index = applicationGui.toDoList.getSelectedIndex();

        String taskDescription = applicationGui.taskModel.getElementAt(index).toString();

        // https://stackoverflow.com/questions/15384486/java-concurrent-modification-exception-error
        // removes task from list when task descriptions are equal
        for (Iterator<Task> iter = applicationGui.myTasks.allTasks.iterator(); iter.hasNext();) {
            Task task = iter.next();
            if (task.getDescription() == taskDescription) {
                iter.remove();
            }
        }
        applicationGui.taskModel.remove(index);
        playSound("data/clickSound.wav");
        int size = applicationGui.taskModel.getSize();
        if (size == 0) {
            applicationGui.removeTaskButton.setEnabled(false);
        } else {
            if (index == applicationGui.taskModel.getSize()) {
                index--;
            }
            applicationGui.toDoList.setSelectedIndex(index);
            applicationGui.toDoList.ensureIndexIsVisible(index);
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
