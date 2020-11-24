package ui.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// saves To-Do list when saveButton is pressed
public class SaveListener implements ActionListener {
    private final ApplicationGui applicationGui;


    // EFFECTS: creates a saveListener
    public SaveListener(ApplicationGui applicationGui) {
        this.applicationGui = applicationGui;
    }


    @Override
    // REQUIRES: saveButton must be pressed
    // EFFECTS: saves ToDoList to a source file
    public void actionPerformed(ActionEvent e) {
        applicationGui.saveToDoList();
        playSound("data/completeTaskSound.wav");
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
