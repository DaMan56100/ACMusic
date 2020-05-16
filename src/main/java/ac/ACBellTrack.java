package main.java.ac;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class ACBellTrack implements ACTrack {
    private ACClipWrapper bellClip;

    public ACBellTrack() throws ACTrackGenerationException {
        try {
            URL bellURL = getClass().getResource(BELL_PATH);
            bellClip = new ACClipWrapper(AudioSystem.getClip(),false);
            bellClip.clip.open(AudioSystem.getAudioInputStream(bellURL));
        } catch (LineUnavailableException e) {
            throw new ACTrackGenerationException(e);
        } catch (IOException e) {
            throw new ACTrackGenerationException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new ACTrackGenerationException(e);
        }
    }

    @Override
    public ACClipWrapper getIntroClip() {
        return null;
    }

    @Override
    public ACClipWrapper getMainClip() {
        return bellClip;
    }

    @Override
    public boolean doesLoopMain() {
        return false;
    }
}
