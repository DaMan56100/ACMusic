package main.java.forms;

import main.java.ClipUtils;
import main.java.ac.*;

import javax.swing.*;
import java.time.LocalDateTime;

public class Player implements IACTrackInfoDisplayer {
    private JFrame displayFrame;
    ACSoundSystem soundSystem = new ACSoundSystem();

    public Player() {
        displayFrame = new JFrame("AC Music Player");
        displayFrame.setContentPane(panMain);
        displayFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        displayFrame.setSize(400,200);
        numVolume.setValue((int)(ClipUtils.DEFAULT_VOLUME * 100));

        btnPlay.addActionListener(e -> soundSystem.play());
        btnStop.addActionListener(e -> soundSystem.pause());
        numVolume.addChangeListener(e -> soundSystem.setVolume(numVolume.getValue() / 100f));
        soundSystem.setTrackInfoDisplayer(this);
    }

    public void setTrackDetails(String name) {
        lblTrackDetails.setText("Now Playing: " + name);
    }

    public void show() {
        displayFrame.setVisible(true);
    }

    private JLabel lblTitle;
    private JPanel panMain;
    private JButton btnPlay;
    private JButton btnStop;
    private JSlider numVolume;
    private JPanel panInterface;
    private JPanel panPlayerControls;
    private JLabel lblTrackDetails;
}
