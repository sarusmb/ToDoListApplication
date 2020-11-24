package ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadListener implements ActionListener {
    private final ApplicationGui applicationGui;

    public LoadListener(ApplicationGui applicationGui) {
        this.applicationGui = applicationGui;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        applicationGui.loadToDoList();
    }
}
