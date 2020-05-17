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

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("All mixers:");
                    Mixer.Info foundInfo = null;
                    for (Mixer.Info m : AudioSystem.getMixerInfo()) {
                        System.out.println(m.getName());
                        if (m.getName().equals("HDMI [plughw:0,8]")) {
                            foundInfo = m;
                        }
                    }

                    System.out.println("Default mixer: " + AudioSystem.getMixer(null).getMixerInfo());
                    //System.out.println("  Using mixer: " + foundInfo.toString());

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
    private JLabel lblTrackDetails;
}
