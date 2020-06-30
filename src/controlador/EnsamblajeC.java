package controlador;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnsamblajeC implements Initializable {
    @FXML
    private MediaView mv;
    MediaPlayer mp;
    Media me;

    @FXML private Slider ProgressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        me = new Media(getClass().getResource("/Video/Ensamblaje.mp4").toExternalForm());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);




        mp.setAutoPlay(true);
        mp.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                ProgressBar.setValue(newValue.toSeconds());
            }
        });

        ProgressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.seek(Duration.seconds(ProgressBar.getValue()));
            }
        });

        ProgressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.seek(Duration.seconds(ProgressBar.getValue()));
            }
        });

        mp.setOnReady(new Runnable() {
            @Override
            public void run() {
                    Duration total = me.getDuration();
                    ProgressBar.setMax(total.toSeconds());
            }
        });

    }
    public void Play(ActionEvent event) throws IOException{
        mp.play();
    }
    public void Pause(ActionEvent event) throws IOException{
        mp.pause();
    }
    public void Cerrar(ActionEvent event) throws IOException{
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
