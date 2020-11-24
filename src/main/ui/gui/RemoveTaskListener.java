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

public class RemoveTaskListener implements ActionListener {
    private final ApplicationGui applicationGui;

    public RemoveTaskListener(ApplicationGui applicationGui) {
        this.applicationGui = applicationGui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = applicationGui.toDoList.getSelectedIndex();

        String taskDescription = applicationGui.taskModel.getElementAt(index).toString();

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


}
