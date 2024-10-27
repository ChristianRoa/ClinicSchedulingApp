package myproject.javafxproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import myproject.javafxproject.model.clinic.Patient;
import myproject.javafxproject.model.clinic.Timeslot;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class ClinicManagerController {

    @FXML
    protected ComboBox<String> timeslotComboBox, timeslotComboBox2, providerComboBox;

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
    @FXML
    protected void loadProviders(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("UJP Files", "*.ujp"));
        fileChooser.setTitle("Select Provider File");
        File selectedFile = fileChooser.showOpenDialog(null);

        if(selectedFile != null){
            providerComboBox.getItems().clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))){
                String line;
                while((line = reader.readLine()) != null){
                    providerComboBox.getItems().add(line.trim());
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

}