package main.java.ac;

import javax.sound.sampled.Clip;

public interface ACTrack {
    String BELL_PATH = "/tracks/chime.wav";
    String TRACKS_CLEAR_DIRECTORY = "/tracks/clear/";

    ACClipWrapper getIntroClip();
    ACClipWrapper getMainClip();
    boolean doesLoopMain();
}
