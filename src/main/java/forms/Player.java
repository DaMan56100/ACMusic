package main.java.forms;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Player {
    private JFrame displayFrame;

    public Player() {
        displayFrame = new JFrame("AC Music Player");
        displayFrame.setContentPane(panMain);
        displayFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        displayFrame.setSize(400,200);

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("All mixers:");
                    for (Mixer.Info m : AudioSystem.getMixerInfo()) {
                        System.out.println(m.getName());
                    }

                    System.out.println("Default mixer: " + AudioSystem.getMixer(null).getMixerInfo());

                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("bsc.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();

                    System.out.println((new File("").getAbsolutePath()));
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }
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
