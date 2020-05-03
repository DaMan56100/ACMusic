package main.java.ac;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class ACHourTrack implements ACTrack {

    private Clip introClip;
    private Clip loopClip;
    private boolean hasIntro;

    public ACHourTrack(int hour) throws ACInvalidHourException, ACTrackGenerationException {
        if (hour < 0 || hour >= 24) {
            throw new ACInvalidHourException(hour,String.format("Hour %d is outside range 0-23",hour));
        }
        String introPath = TRACKS_CLEAR_DIRECTORY + hour + "intro.wav";
        String loopPath = TRACKS_CLEAR_DIRECTORY + hour + ".wav";

        URL introURL = getClass().getResource(introPath); // e.g 0intro.wav
        URL loopURL = getClass().getResource(loopPath); // e.g 0.wav

        hasIntro = false;
        try {
            if (introURL != null) {
                introClip = AudioSystem.getClip();
                introClip.open(AudioSystem.getAudioInputStream(introURL));
                hasIntro = true;
            } else introClip = null;

            if (loopURL != null) {
                loopClip = AudioSystem.getClip();
                loopClip.open(AudioSystem.getAudioInputStream(loopURL));
                loopClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else throw new ACTrackGenerationException(String.format("Track %s doesn't exist in %s",loopPath,TRACKS_CLEAR_DIRECTORY));
        } catch (LineUnavailableException e) {
            throw new ACTrackGenerationException(e);
        } catch (IOException e) {
            throw new ACTrackGenerationException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new ACTrackGenerationException(e);
        }
    }

    public void start() {
        if (hasIntro) {
            introClip.start();
            introClip.addLineListener(event -> {
                if (event.getType().equals(LineEvent.Type.STOP)) playLoop();
            });
        } else playLoop(); // if no intro, go straight into loop
    }

    private void playLoop() {
        loopClip.start();
    }

    public void stop() {
        introClip.stop();
        loopClip.stop();
    }
}
