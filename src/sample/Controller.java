package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class Controller {
    public Label lblHello;

    public void sayHelloWorld(ActionEvent actionEvent) {
        lblHello.setText("Hello World!");

        URL mediaURL = getClass().getResource("/chime.wav");
        Media testMp3 = new Media(mediaURL.toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(testMp3);
        mediaPlayer.setAutoPlay(true);
    }
}
