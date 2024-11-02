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
    @FXML
    public DatePicker appDate, dob, appDate2, dob2;
    @FXML
    public TextField firstName, lastName, firstName2, lastName2;
    private List<Provider> providersList = new List<>();
    private List<Appointment> appointmentList = new List<>();
    private List<Appointment> officeApps = new List<>();
    private List<Appointment> imagingApps = new List<>();
    private List<Doctor> doctorList = new List<>();
    private CircularLinkedList rotationList = new CircularLinkedList();
    private List<Patient> patientList = new List<>();
    @FXML
    private ToggleGroup AppToggleGroup;
    @FXML
    public RadioButton rbutton1, rbutton2;
    @FXML
    private TableView<Location> tbl_location;
    @FXML
    private TableColumn<Location, String> col_zip, col_county, col_city;
    @FXML
    protected Button loadProvidersButton, clearButton, scheduleButton, cancelButton, rescheduleButton, PA, PP, PL, PS, PO, PI, PC;
    @FXML
    protected ComboBox<String> timeslotComboBox, timeslotComboBox2, providerComboBox;
    @FXML
    private TextArea outputTextArea;

    @FXML
    protected void initialize() {
        ObservableList<Location> locations =
                FXCollections.observableArrayList(Location.values());
        tbl_location.setItems(locations);
        col_zip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        col_county.setCellValueFactory(new PropertyValueFactory<>("county"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        populateTimeslots();

        AppToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) ->
                providerComboBox.setDisable(newToggle == rbutton2)
        );
    }

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
                boolean formatError = false;

                while ((line = reader.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line.trim());

                    try {
                        // Ensure the line has at least the common required fields
                        if (st.countTokens() < 5) {
                            throw new IllegalArgumentException("Insufficient data in line.");
                        }

                        String type = st.nextToken();
                        String fname = st.nextToken();
                        String lname = st.nextToken();
                        String dobStr = st.nextToken();
                        Date dob = parseDate(dobStr); // Throws error if date format is incorrect
                        String location = st.nextToken();

                        if (type.equals("D")) {
                            // Doctor requires specialty and NPI fields
                            if (st.countTokens() < 2) {
                                throw new IllegalArgumentException("Missing specialty or NPI for Doctor.");
                            }
                            String specialty = st.nextToken();
                            String npi = st.nextToken();
                            Profile profile = new Profile(fname, lname, dob);
                            Doctor doctor = new Doctor(profile, parseLocation(location), parseSpecialty(specialty), npi);
                            providersList.add(doctor);
                        } else if (type.equals("T")) {
                            // Technician requires a rate field
                            if (!st.hasMoreTokens()) {
                                throw new IllegalArgumentException("Missing rate for Technician.");
                            }
                            int rate;
                            try {
                                rate = Integer.parseInt(st.nextToken());
                            } catch (NumberFormatException e) {
                                throw new IllegalArgumentException("Invalid rate format for Technician.");
                            }
                            Profile profile = new Profile(fname, lname, dob);
                            Technician technician = new Technician(profile, parseLocation(location), rate);
                            providersList.add(technician);
                        } else {
                            throw new IllegalArgumentException("Unknown provider type: " + type);
                        }
                    } catch (Exception e) {
                        // Display a single "Invalid file format" message and stop processing further
                        outputTextArea.appendText("Error: Invalid file format. Please check the file and try again.\n");
                        formatError = true;
                        break; // Exit the loop immediately on first error
                    }
                }

                // If no format error occurred, populate the UI elements
                if (!formatError) {
                    // Print the entire providersList, with both doctors and technicians
                    outputTextArea.appendText("Providers List:\n");
                    for (Provider provider : providersList) {
                        outputTextArea.appendText(provider.toString() + "\n");
                        if (provider instanceof Doctor doctor) {
                            providerComboBox.getItems().add(doctor.toString());
                            doctorList.add(doctor);
                        }
                        if (provider instanceof Technician technician) {
                            rotationList.add(technician);
                        }
                    }

                    // Sort and print the technician rotation list
                    rotationList.sort();
                    outputTextArea.appendText("Technician Rotation List:\n");
                    CircularLinkedList.CircularNode ptr = rotationList.getHead();
                    for (int i = 0; i < rotationList.size(); i++) {
                        outputTextArea.appendText(ptr.getData().getProfile().getFname() + " " + ptr.getData().getProfile().getLname() + " (" + ptr.getData().getLocation() + ")");
                        if (i < rotationList.size() - 1) {
                            outputTextArea.appendText(" --> ");
                        }
                        ptr = ptr.getNext();
                    }
                    outputTextArea.appendText("\n");
                }
            } catch (IOException e) {
                outputTextArea.appendText("Error: Unable to read the file. Please check the file and try again.\n");
                e.printStackTrace(); // Optional: Log stack trace for developers
            }
        }
    }



    @FXML
    private void clearOutputTextArea(Event action) {
        outputTextArea.clear();
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
    @FXML
    private void scheduleAppointment() {
        errorChecks();

        Appointment app = new Appointment(
                new Date(appDate.getValue().getYear(), appDate.getValue().getMonthValue(), appDate.getValue().getDayOfMonth()),
                Timeslot.getTimeSlot(timeslotComboBox.getValue()),
                new Patient(new Profile(firstName.getText(), lastName.getText(), new Date(dob.getValue().getYear(), dob.getValue().getMonthValue(), dob.getValue().getDayOfMonth())), null),
                null
        );

        if (rbutton1.isSelected()) {
            boolean providerFound = false;
            for (Doctor doctor : doctorList) {
                if (doctor.toString().equalsIgnoreCase(providerComboBox.getValue())) {
                    app.setProvider(doctor);
                    providerFound = true;
                    break;
                }
            }
            if (!providerFound) {
                outputTextArea.appendText("Error: Selected provider not found.\n");
                return;
            }
        }
        if (rbutton2.isSelected()) {
            boolean technicianAssigned = false;
            CircularLinkedList.CircularNode startNode = rotationList.getCurrent(); // Store the start node
            do {
                Technician currentTechnician = (Technician) rotationList.getCurrent().getData();
                boolean conflictFound = false;

                for (Appointment existingApp : appointmentList) {
                    if (existingApp.getProvider() != null && existingApp.getProvider().equals(currentTechnician)
                            && existingApp.getDate().equals(app.getDate())
                            && existingApp.getTimeslot().equals(app.getTimeslot())) {
                        conflictFound = true;
                        break;
                    }
                }

                if (!conflictFound) {
                    app.setProvider(currentTechnician);
                    technicianAssigned = true;
                    rotationList.moveToNext(); // Move to the next technician after assignment
                    break; // Exit if technician is assigned
                }

                rotationList.moveToNext(); // Move to next technician in the list

            } while (rotationList.getCurrent() != startNode); // Stop if we've looped back to the start

            if (!technicianAssigned) {
                outputTextArea.appendText("Error: No technicians available for the selected time.\n");
                return;
            }
        }


        boolean conflictFound = false;
        boolean patientConflict = false;
        for (Appointment existingApp : appointmentList) {
            if (existingApp.getProvider() != null
                    && existingApp.getProvider().compareTo(app.getProvider()) == 0
                    && existingApp.getDate().equals(app.getDate())
                    && existingApp.getTimeslot().equals(app.getTimeslot())) {
                conflictFound = true;
                break;
            }

            if (existingApp.getPatient() != null
                    && existingApp.getPatient().compareTo(app.getPatient()) == 0
                    && existingApp.getDate().equals(app.getDate())
                    && existingApp.getTimeslot().equals(app.getTimeslot())) {
                patientConflict = true;
                break;
            }
        }
        if (conflictFound) {
            outputTextArea.appendText("Error: Provider already has an appointment at this time.\n");
            return;
        }
            if (patientConflict) {
                outputTextArea.appendText("Error: Patient already has an appointment at this time.\n");
                return;
            }
        appointmentList.add(app);
        outputTextArea.appendText(app.toString() + " successfully scheduled.\n");
    }

    @FXML
    private void cancelAppointment() {
        errorChecks();
        Appointment app = new Appointment(
                new Date(appDate.getValue().getYear(), appDate.getValue().getMonthValue(), appDate.getValue().getDayOfMonth()),
                Timeslot.getTimeSlot(timeslotComboBox.getValue()),
                new Patient(new Profile(firstName.getText(), lastName.getText(), new Date(dob.getValue().getYear(), dob.getValue().getMonthValue(), dob.getValue().getDayOfMonth())), null),
                null
        );
        for (Appointment existingApp : appointmentList) {
            if (existingApp.getPatient() != null && existingApp.getPatient().compareTo(app.getPatient()) == 0
                    && existingApp.getDate().equals(app.getDate())
                    && existingApp.getTimeslot().equals(app.getTimeslot())) {
                appointmentList.remove(existingApp);
                outputTextArea.appendText(existingApp.toString() + " successfully cancelled.\n");
                return;
            }
        }
        outputTextArea.appendText("Error: appointment not found.\n");
    }

    @FXML
    private void rescheduleAppointment(){
        errorChecks2();
        Appointment compareApp = new Appointment(new Date(appDate2.getValue().getYear(), appDate2.getValue().getMonthValue(), appDate2.getValue().getDayOfMonth()),
                Timeslot.getTimeSlot(timeslotComboBox2.getValue()),
                new Patient(new Profile(firstName2.getText(), lastName2.getText(), new Date(dob2.getValue().getYear(), dob2.getValue().getMonthValue(), dob2.getValue().getDayOfMonth())), null),
                null);
        for (Appointment existingApp : appointmentList) {
            if (existingApp.getDate().equals(compareApp.getDate()) && existingApp.getPatient().compareTo(compareApp.getPatient()) == 0){
                outputTextArea.appendText(existingApp.toString() + " successfully rescheduled to " + compareApp.getTimeslot().toString() + ".\n");
                existingApp.setTimeslot(compareApp.getTimeslot());
                return;
            }
        }
        outputTextArea.appendText("Error: appointment not found.");
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

    private void errorChecks() {
        if (appDate.getValue() == null) {
            outputTextArea.appendText("Error: Please select an appointment date.\n");
        } else {
            invalidAppDate(new Date(appDate.getValue().getYear(), appDate.getValue().getMonthValue(), appDate.getValue().getDayOfMonth()));
        }

        String firstNameText = firstName.getText().trim();
        if (firstNameText.isEmpty()) {
            outputTextArea.appendText("Error: First name is required.\n");
        } else if (!firstNameText.matches("[a-zA-Z]+")) {
            outputTextArea.appendText("Error: First name can only contain alphabetic characters.\n");
        }

        String lastNameText = lastName.getText().trim();
        if (lastNameText.isEmpty()) {
            outputTextArea.appendText("Error: Last name is required.\n");
        } else if (!lastNameText.matches("[a-zA-Z]+")) {
            outputTextArea.appendText("Error: Last name can only contain alphabetic characters.\n");
        }

        if (dob.getValue() == null) {
            outputTextArea.appendText("Error: Date of birth is required.\n");
        } else {
            invalidDob(new Date(dob.getValue().getYear(), dob.getValue().getMonthValue(), dob.getValue().getDayOfMonth()));
        }

        if (timeslotComboBox.getValue() == null) {
            outputTextArea.appendText("Error: Please select a timeslot.\n");
        }

        if (AppToggleGroup.getSelectedToggle() == null) {
            outputTextArea.appendText("Error: Please select either Office or Imaging Service.\n");
        } else if (rbutton1.isSelected() && providerComboBox.getValue() == null) {
            outputTextArea.appendText("Error: Please select a provider when Office is selected.\n");
        }
    }

    private void errorChecks2() {
        if (appDate2.getValue() == null) {
            outputTextArea.appendText("Error: Please select an appointment date.\n");
        } else {
            invalidAppDate(new Date(appDate2.getValue().getYear(), appDate2.getValue().getMonthValue(), appDate2.getValue().getDayOfMonth()));
        }

        String firstNameText = firstName2.getText().trim();
        if (firstNameText.isEmpty()) {
            outputTextArea.appendText("Error: First name is required.\n");
        } else if (!firstNameText.matches("[a-zA-Z]+")) {
            outputTextArea.appendText("Error: First name can only contain alphabetic characters.\n");
        }

        String lastNameText = lastName2.getText().trim();
        if (lastNameText.isEmpty()) {
            outputTextArea.appendText("Error: Last name is required.\n");
        } else if (!lastNameText.matches("[a-zA-Z]+")) {
            outputTextArea.appendText("Error: Last name can only contain alphabetic characters.\n");
        }

        if (dob2.getValue() == null) {
            outputTextArea.appendText("Error: Date of birth is required.\n");
        } else {
            invalidDob(new Date(dob2.getValue().getYear(), dob2.getValue().getMonthValue(), dob2.getValue().getDayOfMonth()));
        }

        if (timeslotComboBox2.getValue() == null) {
            outputTextArea.appendText("Error: Please select a timeslot.\n");
        }
    }
    @FXML
    private void selectPA() {
        if (appointmentList.isEmpty()) {
            outputTextArea.appendText("Error: No appointments available to display.\n");
            return;
        }
        Sort.appointment(appointmentList, 'A');
        outputTextArea.appendText(" ** List of appointments, ordered by date/time/provider.\n");
        for(Appointment apps: appointmentList){
            outputTextArea.appendText(apps.toString() + "\n" );
        }
        outputTextArea.appendText("** end of list **\n");
    }
    @FXML
    private void selectPP() {
        if (appointmentList.isEmpty()) {
            outputTextArea.appendText("Error: No appointments available to display.\n");
            return;
        }
        Sort.appointment(appointmentList, 'P');
        outputTextArea.appendText(" ** List of appointments, ordered by patient/date/time.\n");
        for(Appointment apps: appointmentList){
            outputTextArea.appendText(apps.toString() + "\n" );
        }
        outputTextArea.appendText("** end of list **\n");
    }
    @FXML
    private void selectPL() {
        if (appointmentList.isEmpty()) {
            outputTextArea.appendText("Error: No appointments available to display.\n");
            return;
        }
        Sort.appointment(appointmentList, 'L');
        outputTextArea.appendText(" ** List of appointments, ordered by county/date/time.\n");
        for(Appointment apps: appointmentList){
            outputTextArea.appendText(apps.toString() + "\n" );
        }
        outputTextArea.appendText("** end of list **\n");
    }
    @FXML
    private void selectPS() {
        if (appointmentList.isEmpty()) {
            outputTextArea.appendText("Error: No appointments available to display billing.\n");
            return;
        }
        outputTextArea.appendText("** Billing Statements. **\n");
        for (Appointment appointment : appointmentList) {
            Patient patient = appointment.getPatientAsPatient();
            Patient existingPatient = null;
            for (Patient p : patientList) {
                if (p.getProfile().equals(patient.getProfile())) {
                    existingPatient = p;
                    break;
                }
            }
            if (existingPatient != null) {
                Visit newVisit = new Visit(appointment, existingPatient.getVisits());
                existingPatient.setVisits(newVisit);
            } else {
                Visit newVisit = new Visit(appointment, null);
                patient.setVisits(newVisit);
                patientList.add(patient);
            }
        }
        for (Patient patient : patientList) {
            outputTextArea.appendText(patient.toString() + "$\n");
        }
        outputTextArea.appendText("** End of Billing Statements. **\n");
    }
    @FXML
    private void selectPO() {
        if (appointmentList.isEmpty()) {
            outputTextArea.appendText("Error: No office appointments available to display.\n");
            return;
        }
        outputTextArea.appendText("** List of Office Appointments **\n");
        for(Appointment apps : appointmentList){
            if(apps.getProvider() instanceof Doctor){
                officeApps.add(apps);
            }
        }
        for(Appointment apps : officeApps){
            outputTextArea.appendText(apps.toString() + "\n");
        }
        outputTextArea.appendText("** End of List **\n");
    }
    @FXML
    private void selectPI() {
        if (appointmentList.isEmpty()) {
            outputTextArea.appendText("Error: No imaging appointments available to display.\n");
            return;
        }
        outputTextArea.appendText("** List of Imaging Appointments **\n");
        for(Appointment apps : appointmentList){
            if(apps.getProvider() instanceof Technician){
                imagingApps.add(apps);
            }
        }
        for(Appointment apps : imagingApps){
            outputTextArea.appendText(apps.toString() + "\n");
        }
        outputTextArea.appendText("** End of List **\n");
    }
    @FXML
    private void selectPC() {
        if (providersList.isEmpty()) {
            outputTextArea.appendText("Error: No providers available to calculate credits.\n");
            return;
        }
        outputTextArea.appendText("** Credit amount ordered by provider. **\n");
        Sort.provider(providersList);
        for(Provider provider : providersList){
            double money = 0;
            for(Appointment app : appointmentList){
                if(app.getProviderAsProvider().compareTo(provider) == 0){
                    money += provider.getRate();
                }
            }
            outputTextArea.appendText(provider.toString() + " " + money + "$.\n");
        }
        outputTextArea.appendText("** End of Credit amount. **\n");
    }
    private void invalidAppDate(Date date) {
        if (!date.isValidDate()) {
            outputTextArea.appendText("Error: Appointment Date is invalid.\n");
        }
        if (date.isBeforeToday()) {
            outputTextArea.appendText("Error: Appointment Date is before today.\n");
        }
        if (!date.isWeekday()) {
            outputTextArea.appendText("Error: Appointment Date is not a weekday.\n");
        }
        if (!date.isWithinSixMonths()) {
            outputTextArea.appendText("Error: Appointment Date is not within six months.\n");
        }
    }
    private void invalidDob(Date date) {
        if (!date.isValidDate()) {
            outputTextArea.appendText("Error: Date of Birth is invalid.\n");
        }
        if (!date.isBeforeToday()) {
            outputTextArea.appendText("Error: Date of Birth must be before today.\n");
        }
    }

}
