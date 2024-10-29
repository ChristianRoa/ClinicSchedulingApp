module myproject.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens myproject.javafxproject.model.clinic to javafx.base;
    opens myproject.javafxproject to javafx.fxml;
    exports myproject.javafxproject;
    exports myproject.javafxproject.controller;
    opens myproject.javafxproject.controller to javafx.fxml;
}