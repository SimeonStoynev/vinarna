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
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;

    opens bg.tu_varna.sit.vinarna.data.entities to org.hibernate.orm.core;
    exports bg.tu_varna.sit.vinarna.data.entities;

    opens bg.tu_varna.sit.vinarna.data.mysql to org.hibernate.orm.core;
    exports bg.tu_varna.sit.vinarna.data.mysql;


    exports bg.tu_varna.sit.vinarna.application;
    opens bg.tu_varna.sit.vinarna.application to javafx.fxml;

    exports bg.tu_varna.sit.vinarna.presentation.controllers;
    opens bg.tu_varna.sit.vinarna.presentation.controllers to javafx.fxml;
}