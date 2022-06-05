module com.example.applicativoprogetto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    exports com.example.view;
    opens com.example.view to javafx.fxml;
    opens com.example.controller to javafx.fxml;
    opens com.example.model to javafx.base;
}