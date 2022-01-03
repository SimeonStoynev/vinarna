package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.DashboardService;
import bg.tu_varna.sit.vinarna.business.NotificationService;
import bg.tu_varna.sit.vinarna.common.UserSession;
import bg.tu_varna.sit.vinarna.common.ViewsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;

public class DashboardController {
    private static final Logger log = Logger.getLogger(LoginController.class);

    NotificationService notificationService = NotificationService.getInstance();

    DashboardService dashboardService = DashboardService.getInstance();

    @FXML
    AnchorPane menuPane;

    @FXML
    AnchorPane mainContentPane;

    @FXML
    AnchorPane notificationAnchorPane;

    @FXML
    AnchorPane notificationRows;

    @FXML
    AnchorPane norifCountAnchorPane;

    @FXML
    Label notifCountLabel;


    @FXML
    private void initialize() {
        menuBuild();
        notificationAnchorPane.setVisible(false);
        norifCountAnchorPane.setVisible(false);

        dashboardService.backgroundWorkerRun(notificationRows, norifCountAnchorPane, notifCountLabel);
    }

    private void menuBuild() {
        ViewsManager.leftMenuGenerate(menuPane, mainContentPane, DashboardController.class);
    }

    public void appExit() {
        System.exit(1);
    }

    public void notificationButtonClick() {
        if(!notificationAnchorPane.isVisible()) {
            notificationAnchorPane.setVisible(true);
            notificationService.setSeenByUser(UserSession.user);
        } else {
            notificationAnchorPane.setVisible(false);
        }
    }
}
