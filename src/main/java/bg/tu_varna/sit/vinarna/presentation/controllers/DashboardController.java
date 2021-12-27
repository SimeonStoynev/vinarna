package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.common.ViewsManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;

import java.io.IOException;

public class DashboardController {

    private static final Logger log = Logger.getLogger(LoginController.class);

    @FXML
    AnchorPane menuPane;

    @FXML
    AnchorPane mainContentPane;

    @FXML
    private void initialize() throws IOException {
        menuBuild();

        //AnchorPane sp = FXMLLoader.load(getClass().getResource(Constants.View.LOGIN_VIEW));
        //ViewsManager.setAndClearAnchorPane(sp, mainContentPane);
    }

    private void menuBuild() {
        ViewsManager.leftMenuGenerate(menuPane, mainContentPane, DashboardController.class);
    }

    public void appExit() {
        System.exit(1);
    }
}
