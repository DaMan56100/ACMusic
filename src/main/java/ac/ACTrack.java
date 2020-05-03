package main.java.ac;

import javax.sound.sampled.Clip;

public interface ACTrack {
    final String BELL_PATH = "/tracks/chime.wav";
    final String TRACKS_CLEAR_DIRECTORY = "/tracks/clear/";

    public int volume = 1;

    void start();
    void stop();
}
