package main.java.ac;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class ACBellTrack implements ACTrack {
    private Clip bellClip;

    public ACBellTrack() throws ACTrackGenerationException {
        try {
            URL bellURL = getClass().getResource(BELL_PATH);
            bellClip = AudioSystem.getClip();
            bellClip.open(AudioSystem.getAudioInputStream(bellURL));
        } catch (LineUnavailableException e) {
            throw new ACTrackGenerationException(e);
        } catch (IOException e) {
            throw new ACTrackGenerationException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new ACTrackGenerationException(e);
        }
    }

    @Override
    public void start() {
        bellClip.start();
    }

    @Override
    public void stop() {
        bellClip.stop();
    }
}
