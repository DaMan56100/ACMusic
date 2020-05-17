package main.java.ac;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ACTrackGenerationException extends Exception {
    public ACTrackGenerationException() {
    }

    public ACTrackGenerationException(String message) {
        super(message);
    }

    public ACTrackGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ACTrackGenerationException(Throwable cause) {
        super(cause);
    }

    public ACTrackGenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ACTrackGenerationException(LineUnavailableException e) {
        this("Line was unavailable",e);
    }

    public ACTrackGenerationException(IOException e) {
        this("IO Exception",e);
    }

    public ACTrackGenerationException (UnsupportedAudioFileException e) {
        this("Didn't support audio file",e);
    }
}
