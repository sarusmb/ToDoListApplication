package ui.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveListener implements ActionListener {
    private final ApplicationGui applicationGui;

    public SaveListener(ApplicationGui applicationGui) {
        this.applicationGui = applicationGui;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        applicationGui.saveToDoList();
    }

}
