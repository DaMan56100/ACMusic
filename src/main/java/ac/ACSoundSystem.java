package main.java.ac;

import java.time.LocalDateTime;

public class ACSoundSystem {
    private ACSoundPlayer soundPlayer = new ACSoundPlayer();

    public void setVolume(float volume) {
        soundPlayer.setVolume(volume);
    }

    public void play() {
        try {
            int hour = LocalDateTime.now().getHour();
            ACHourTrack hourTrack = new ACHourTrack(hour);
            soundPlayer.playTrack(hourTrack);
        } catch (ACTrackGenerationException ex) {
            ex.printStackTrace();
        }
    }

    public void pause() {
        try {
            ACBellTrack bellTrack = new ACBellTrack();
            soundPlayer.playTrack(bellTrack);
        } catch (ACTrackGenerationException ex) {
            ex.printStackTrace();
        }
    }
}
