package main.java.ac;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class ACHourTrack implements ACTrack {
    private ACClipWrapper introClip;
    private ACClipWrapper loopClip;
    private boolean hasIntro;
    private String name;

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
                introClip = new ACClipWrapper(AudioSystem.getClip(),false);
                introClip.clip.open(AudioSystem.getAudioInputStream(introURL));
                hasIntro = true;
            } else introClip = null;

            if (loopURL != null) {
                loopClip = new ACClipWrapper(AudioSystem.getClip(),true);
                loopClip.clip.open(AudioSystem.getAudioInputStream(loopURL));
            } else throw new ACTrackGenerationException(String.format("Track %s doesn't exist in %s",loopPath,TRACKS_CLEAR_DIRECTORY));
        } catch (LineUnavailableException e) {
            throw new ACTrackGenerationException(e);
        } catch (IOException e) {
            throw new ACTrackGenerationException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new ACTrackGenerationException(e);
        }

        name = parseHourToString(hour);
    }

    @Override
    public ACClipWrapper getIntroClip() {
        return introClip;
    }

    @Override
    public ACClipWrapper getMainClip() {
        return loopClip;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Hour stamps are weird. This converts an int hour into a correct AM/PM hour
     * @param hour Hour in range 0-23
     * @return A string of the hour, e.g 0 -> "12AM", 22 -> "10PM"
     */
    private static String parseHourToString(int hour) {
        if (hour == 0) return "12AM";
        if (hour > 0 && hour < 12) return hour + "AM";
        if (hour == 12) return "12PM";
        if (hour > 12 && hour < 24) return (hour-12) + "PM";
        throw new IllegalArgumentException("Hour not in range 0-23");
    }
}
