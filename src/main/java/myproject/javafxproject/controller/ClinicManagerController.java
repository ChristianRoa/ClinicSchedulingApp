package myproject.javafxproject.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import myproject.javafxproject.model.clinic.*;
import myproject.javafxproject.model.utilities.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ClinicManagerController {
    private List<Provider> providersList = new List<>();

    @FXML
    private ToggleGroup AppToggleGroup;
    @FXML
    public RadioButton rbutton1, rbutton2;
    @FXML
    private TableView<Location> tbl_location;
    @FXML
    private TableColumn<Location, String> col_zip, col_county, col_city;
    @FXML
    protected Button loadProvidersButton, clearButton;
    @FXML
    protected ComboBox<String> timeslotComboBox, timeslotComboBox2, providerComboBox;
    @FXML
    private TextArea outputTextArea;

    private void populateTimeslots() {
        for (int[] timeslot : Timeslot.validTimeslots) {
            Timeslot slot = new Timeslot(timeslot[0], timeslot[1]);
            timeslotComboBox.getItems().add(slot.toString());
            timeslotComboBox2.getItems().add(slot.toString());
        }
    }

    @FXML
    protected void loadProviders(ActionEvent event) {
        loadProvidersButton.setDisable(true);
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        fileChooser.setTitle("Select Provider File");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    outputTextArea.appendText(line + "\n");  // Print each line to TextArea
                    StringTokenizer st = new StringTokenizer(line.trim());
                    String type = st.nextToken(); // Assume 'D' for Doctor or 'T' for Technician
                    String fname = st.nextToken();
                    String lname = st.nextToken();
                    String dobStr = st.nextToken();
                    Date dob = parseDate(dobStr); // Use your date parsing method
                    String location = st.nextToken();

                    if (type.equals("D")) {
                        String specialty = st.nextToken();
                        String npi = st.nextToken();
                        Profile profile = new Profile(fname, lname, dob);
                        Doctor doctor = new Doctor(profile, parseLocation(location), parseSpecialty(specialty), npi);
                        providersList.add(doctor);
                    } else if (type.equals("T")) {
                        int rate = Integer.parseInt(st.nextToken());
                        Profile profile = new Profile(fname, lname, dob);
                        Technician technician = new Technician(profile, parseLocation(location), rate);
                        providersList.add(technician);
                    }
                }
                for (Provider provider : providersList) {
                    if (provider instanceof Doctor doctor) {
                        providerComboBox.getItems().add(doctor.getProfile().getFname() + " " + doctor.getProfile().getLname() + " (" + doctor.getNpi() + ")");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Specialty parseSpecialty(String specialtyString) {
        if (specialtyString == null || specialtyString.isEmpty()) {
            return null;
        }

        specialtyString = specialtyString.toUpperCase().trim();

        return switch (specialtyString) {
            case "FAMILY" -> Specialty.FAMILY;
            case "PEDIATRICIAN" -> Specialty.PEDIATRICIAN;
            case "ALLERGIST" -> Specialty.ALLERGIST;
            default -> null;
        };
    }

    private Location parseLocation(String locationString) {
        if (locationString == null || locationString.isEmpty()) {
            return null;
        }

        locationString = locationString.toUpperCase().trim();

        return switch (locationString) {
            case "BRIDGEWATER" -> Location.BRIDGEWATER;
            case "EDISON" -> Location.EDISON;
            case "PISCATAWAY" -> Location.PISCATAWAY;
            case "PRINCETON" -> Location.PRINCETON;
            case "MORRISTOWN" -> Location.MORRISTOWN;
            case "CLARK" -> Location.CLARK;
            default -> null;
        };
    }

    private Date parseDate(String dateString) {
        StringTokenizer tokenizer = new StringTokenizer(dateString, "/");

        int month = Integer.parseInt(tokenizer.nextToken());  // Get month
        int day = Integer.parseInt(tokenizer.nextToken());    // Get day
        int year = Integer.parseInt(tokenizer.nextToken());   // Get year

        return new Date(year, month, day);
    }

    @FXML
    private void clearOutputTextArea(Event action) {
        outputTextArea.clear();
    }

    @FXML
    protected void initialize() {
        ObservableList<Location> locations =
                FXCollections.observableArrayList(Location.values());
        tbl_location.setItems(locations);
        col_zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        col_county.setCellValueFactory(new PropertyValueFactory<>("county"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        populateTimeslots();

        AppToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == rbutton2) {
                providerComboBox.setDisable(true);
            } else {
                providerComboBox.setDisable(false);
            }
        });
    }
}
