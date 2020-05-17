package main.java.ac;

import main.java.ClipUtils;
import main.java.forms.Player;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import java.util.*;

public class ACSoundPlayer {
    private ACClipWrapper currentClip;
    private float volume;
    private IACTrackInfoDisplayer trackInfoDisplayer;
    private boolean stoppingAllClips = false;

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        if (Objects.nonNull(currentClip)) ClipUtils.setVolumeOfClip(currentClip.clip,volume);
    }

    public void setTrackInfoDisplayer(IACTrackInfoDisplayer trackInfoDisplayer) {
        this.trackInfoDisplayer = trackInfoDisplayer;
    }

    public ACSoundPlayer() {
        volume = ClipUtils.DEFAULT_VOLUME;
    }

    public void playTrack(ACTrack track) {
        stopAll(); // clears any existing tracks

        playClipSequence(
                track.getIntroClip(),
                track.getMainClip()
        );

        if (Objects.nonNull(trackInfoDisplayer)) trackInfoDisplayer.setTrackDetails(track.getName());
    }

    public void stopAll() {
        stoppingAllClips = true;
        if (Objects.nonNull(currentClip)) currentClip.clip.stop();
        stoppingAllClips = false;
    }

    private void playClipSequence(ACClipWrapper... clips) {
        Queue<ACClipWrapper> clipQueue = new LinkedList<>(Arrays.asList(clips));
        playClipSequence(clipQueue);
    }

    private void playClipSequence(Queue<ACClipWrapper> clipQueue) {
        ACClipWrapper firstClip = clipQueue.remove();
        if (Objects.nonNull(firstClip)) {
            firstClip.clip.addLineListener(event -> {
                if (stoppingAllClips) return; // if stopAll() was called, forget the queue
                // at stop, play the next clip (if there is one);
                if (event.getType().equals(LineEvent.Type.STOP)) {
                    if (!clipQueue.isEmpty()) playClipSequence(clipQueue);
                    else currentClip = null; // if queue is empty, current clip is now null
                }
            });
            playClip(firstClip);
        } else { // if clip is null, skip to next one
            playClipSequence(clipQueue);
        }
    }

    private void playClip(ACClipWrapper clip) {
        currentClip = clip;
        ClipUtils.setVolumeOfClip(clip.clip,volume);
        if (clip.loop) {
            clip.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.clip.start();
        }
    }
}
