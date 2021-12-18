module bg.tu_varna.sit.vinarna {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires log4j;


    exports bg.tu_varna.sit.vinarna.application;
    opens bg.tu_varna.sit.vinarna.application to javafx.fxml;

    exports bg.tu_varna.sit.vinarna.presentation.controllers;
    opens bg.tu_varna.sit.vinarna.presentation.controllers to javafx.fxml;
}