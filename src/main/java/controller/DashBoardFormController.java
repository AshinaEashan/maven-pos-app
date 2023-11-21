package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashBoardFormController {

    public Label dateLabel;
    public Label timeLabel;

    public void initialize(){
        genTime();
        genDate();
    }
    private void genTime(){
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> timeLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")))
        ), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    private void genDate() {
        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        dateLabel.setText(formattedDate);

    }
    public void customerButtonOnAction(ActionEvent actionEvent) {
    }

    public void orderButtonOnAction(ActionEvent actionEvent) {
    }

    public void orderDetailsButtonOnAction(ActionEvent actionEvent) {
    }

    public void itemButtonOnAction(ActionEvent actionEvent) {
    }
}
