package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.common.ViewsManager;
import bg.tu_varna.sit.vinarna.data.entities.GrapeStorage;
import bg.tu_varna.sit.vinarna.data.repositories.GrapeStorageRepository;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;

import java.util.List;

public class DashboardController {

    private static final Logger log = Logger.getLogger(LoginController.class);

    GrapeStorageRepository grapeStorageRepository = GrapeStorageRepository.getInstance();

    @FXML
    AnchorPane menuPane;

    @FXML
    AnchorPane mainContentPane;

    @FXML
    private void initialize() {
        menuBuild();

        GrapeStorageRepository tep = GrapeStorageRepository.getInstance();
        //List<GrapeStorage> gs = tep.getAll();
        //System.out.println(tep.getLastByGrapeSortId(1));
    }

    private void menuBuild() {
        ViewsManager.leftMenuGenerate(menuPane, mainContentPane, DashboardController.class);
    }

    public void appExit() {
        System.exit(1);
    }
}
