package main.java;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

//TODO: needs proper sound system, so when volume changes it updates everything
public class ClipUtils {
    public static final float DEFAULT_VOLUME = 1f;

    public static void setVolumeOfClip(Clip clip, float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
}
