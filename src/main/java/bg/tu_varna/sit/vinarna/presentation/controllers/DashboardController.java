package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.common.ViewsManager;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;

public class DashboardController {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @FXML
    AnchorPane menuPane;

    @FXML
    private void initialize() {
        menuBuild();
    }

    private void menuBuild() {
        ViewsManager.leftMenuGenerate(menuPane, DashboardController.class);
    }

}
