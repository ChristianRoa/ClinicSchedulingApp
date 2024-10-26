package myproject.javafxproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import myproject.javafxproject.model.clinic.Patient;
import myproject.javafxproject.model.clinic.Timeslot;

import javax.swing.*;

public class ClinicManagerController {

    @FXML
    protected ComboBox<String> timeslotComboBox, timeslotComboBox2;

    @FXML
    protected void initialize() {
        populateTimeslots();
    }

    private void populateTimeslots() {
        for(int[] timeslot : Timeslot.validTimeslots){
            Timeslot slot = new Timeslot(timeslot[0], timeslot[1]);
            timeslotComboBox.getItems().add(slot.toString());
            timeslotComboBox2.getItems().add(slot.toString());
        }
    }


}