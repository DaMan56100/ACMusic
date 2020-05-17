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
            fadeOutAtNextHour();
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

    public void fadeOutAtNextHour() {
        new Thread(() -> {
            boolean fadingOut = false;
            while (!fadingOut) {
                LocalDateTime currentTime = LocalDateTime.now();
                if (
                     currentTime.getMinute() == 59 &&
                     currentTime.getSecond() >= 52
                ) fadingOut = true;
                else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            float currentVolume = soundPlayer.getVolume();
            for (float i = 1; i >= 0.2; i-=0.001f) {
                soundPlayer.setVolume(currentVolume * i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            soundPlayer.setVolume(currentVolume);
            try {
                soundPlayer.playTrack(new ACBellTrack());
                Thread.sleep(15000);
            } catch (ACTrackGenerationException | InterruptedException e) {
                e.printStackTrace();
            }
            play();
        }).start();
    }
}
