package myproject.javafxproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import myproject.javafxproject.model.clinic.Patient;

public class ClinicManagerController {

    @FXML
    protected TabPane mainTabPane;
    @FXML
    protected DatePicker datePicker;


    public void onRescheduleButtonClick(ActionEvent actionEvent) {
    }
    public void onScheduleButtonClick(ActionEvent event) {
        // Your code here for scheduling the appointment
    }
    public void onCancelButtonClick(ActionEvent event) {
        // Your code for canceling the operation
    }
}