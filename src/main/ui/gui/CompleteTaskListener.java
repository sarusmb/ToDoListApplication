package ui.gui;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class CompleteTaskListener implements ActionListener {
    private final ApplicationGui applicationGui;
    private String checkMark;

    public CompleteTaskListener(ApplicationGui applicationGui) {
        this.applicationGui = applicationGui;
        checkMark = "✓";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int index = applicationGui.tasks.getSelectedIndex();
        String taskDescription = applicationGui.taskModel.getElementAt(index).toString();

        if (!taskDescription.contains(checkMark)) {
            taskDescription = checkMark + "  " + taskDescription;
            playSound("data/completeTaskSound.wav");
            applicationGui.taskModel.remove(index);
            applicationGui.taskModel.insertElementAt(taskDescription,index);
            applicationGui.tasks.setSelectedIndex(index);
            applicationGui.tasks.ensureIndexIsVisible(index);
        } else {
            playSound("data/errorSound.wav");
            return;
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

