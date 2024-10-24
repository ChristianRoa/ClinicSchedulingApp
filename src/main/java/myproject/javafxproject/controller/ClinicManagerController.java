package myproject.javafxproject.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import myproject.javafxproject.model.clinic.Patient;

public class ClinicManagerController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onSubmitButtonClick() {
        // Handle button click
        System.out.println("Button clicked!");
    }

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField nameTextsField;

    @FXML
    private RadioButton option1RadioButton;
    @FXML
    private RadioButton option2RadioButton;

    @FXML
    private TextArea commentsTextArea;

    @FXML
    private TableView<Patient> patientsTableView;
    @FXML
    private TableColumn<Patient, String> nameColumn;
    @FXML
    private TableColumn<Patient, Integer> ageColumn;

    @FXML
    private TabPane tabPane;

    @FXML
    private GridPane formGridPane;



}