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
    protected RadioButton rbutton1, rbutton2;


    @FXML
    protected ComboBox<String> timeslotComboBox;

    @FXML
    protected void initialize() {
        populateTimeslots();
        selectApp();
    }

    private void populateTimeslots() {
        for(int[] timeslot : Timeslot.validTimeslots){
            Timeslot slot = new Timeslot(timeslot[0], timeslot[1]);
            timeslotComboBox.getItems().add(slot.toString());
        }
    }

    protected void selectApp() {
        rbutton1.setSelected(true);
        if(rbutton2.isSelected()){
            rbutton1.setSelected(false);
        }
    }

}