package ui.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// loads a To-Do list when loadButton is pressed
public class LoadListener implements ActionListener {
    private final ApplicationGui applicationGui;

    // EFFECTS : creates a loadListener
    public LoadListener(ApplicationGui applicationGui) {
        this.applicationGui = applicationGui;

    }

    @Override
    // REQUIRES: loadButton must be pressed
    // EFFECTS: loads ToDoList from source file to To-Do List application
    public void actionPerformed(ActionEvent e) {
        applicationGui.loadToDoList();
        playSound("data/completeTaskSound.wav");
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
}
