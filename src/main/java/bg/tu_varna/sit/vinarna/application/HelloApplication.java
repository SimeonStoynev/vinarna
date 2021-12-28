package bg.tu_varna.sit.vinarna.application;

import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.common.ViewsManager;
import bg.tu_varna.sit.vinarna.presentation.controllers.DashboardController;
import bg.tu_varna.sit.vinarna.presentation.controllers.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;

public class HelloApplication extends Application {

    private static final Logger log = Logger.getLogger(HelloApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        ViewsManager.changeView(Constants.Values.TITLE, Constants.View.DASHBOARD_VIEW, DashboardController.class, stage);
    }

    public static void main(String[] args) {
        launch();
    }
}