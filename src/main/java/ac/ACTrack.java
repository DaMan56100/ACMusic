package main.java.ac;

public interface ACTrack {
    String BELL_PATH = "/tracks/chime.wav";
    String TRACKS_CLEAR_DIRECTORY = "/tracks/clear/";

    void start();
    void stop();
}
