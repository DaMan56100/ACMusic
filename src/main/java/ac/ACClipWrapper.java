package main.java.ac;

import javax.sound.sampled.Clip;

/**
 * Exists to wrap Clip with an option as to whether it is a looping clip or not
 */
public class ACClipWrapper {
    Clip clip;
    boolean loop;

    public ACClipWrapper(Clip clip, boolean loop) {
        this.clip = clip;
        this.loop = loop;
    }
}
