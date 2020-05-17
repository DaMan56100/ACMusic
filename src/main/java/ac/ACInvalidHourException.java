package main.java.ac;

public class ACInvalidHourException extends RuntimeException {
    private final int invalidHour;

    public int getInvalidHour() {
        return invalidHour;
    }

    public ACInvalidHourException(int invalidHour) {
        this.invalidHour = invalidHour;
    }

    public ACInvalidHourException(int invalidHour, String message) {
        super(message);
        this.invalidHour = invalidHour;
    }

    public ACInvalidHourException(int invalidHour, String message, Throwable cause) {
        super(message, cause);
        this.invalidHour = invalidHour;
    }

    public ACInvalidHourException(int invalidHour, Throwable cause) {
        super(cause);
        this.invalidHour = invalidHour;
    }

    public ACInvalidHourException(int invalidHour, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.invalidHour = invalidHour;
    }
}
