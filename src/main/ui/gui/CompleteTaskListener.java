package ui.gui;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import model.Task;


public class CompleteTaskListener implements ActionListener {
    private final ApplicationGui applicationGui;
    private String checkMark;

    public CompleteTaskListener(ApplicationGui applicationGui) {
        this.applicationGui = applicationGui;
        checkMark = "✓";
    }

    @Override
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


    public void setTaskAsCompleted(String taskDescription) {
        for (Task task : applicationGui.myTasks.allTasks) {
            if (task.getDescription() == taskDescription) {
                task.editDescription(checkMark + "  " + taskDescription);
                task.changeStatus(true);
            }
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

