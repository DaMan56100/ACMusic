package main.java.forms;

import main.java.ac.ACBellTrack;
import main.java.ac.ACHourTrack;
import main.java.ac.ACTrack;
import main.java.ac.ACTrackGenerationException;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

public class Player {
    private JFrame displayFrame;

    public Player() {
        displayFrame = new JFrame("AC Music Player");
        displayFrame.setContentPane(panMain);
        displayFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        displayFrame.setSize(400,200);

        btnPlay.addActionListener(e -> {
            try {
                int hour = LocalDateTime.now().getHour();
                ACHourTrack hourTrack = new ACHourTrack(hour);
                hourTrack.start();
            } catch (ACTrackGenerationException ex) {
                ex.printStackTrace();
            }
        });
        btnStop.addActionListener(e -> {
            try {
                ACBellTrack bellTrack = new ACBellTrack();
                bellTrack.start();
            } catch (ACTrackGenerationException ex) {
                ex.printStackTrace();
            }
        });
        numVolume.addChangeListener(e -> {
            ACTrack.volume =
        });
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
}
