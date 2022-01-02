package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.GrapeStorageService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeStorageModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ReferencesGrapeAnchorPaneController {
    private static final Logger log = Logger.getLogger(UsersAnchorPaneController.class);

    GrapeStorageService grapeStorageService = GrapeStorageService.getInstance();

    @FXML
    AnchorPane tableAnchorPane;

    @FXML
    GridPane errorGridPane;

    GrapeSortModel grapeSort;
    LocalDate startDate;
    LocalDate endDate;

    ObservableList<GrapeStorageModel> grapeStorage;

    @FXML
    public void initialize() {
        errorGridPane.setVisible(false);
    }

    public void init(GrapeSortModel grapeSort, LocalDate startDate, LocalDate endDate) {
        this.grapeSort = grapeSort;
        this.startDate = startDate;
        this.endDate = endDate;

        grapeStorage = grapeStorageService.getLatestAllByGrapeAndPeriod(grapeSort, startDate, endDate);
        System.out.println(grapeStorage);
        tableViewReload();
    }

    public void tableViewReload() {
        try {
            tableAnchorPane.getChildren().clear();
            int y = 0;
            boolean bg = false;
            LocalDate date = new Timestamp(0).toLocalDateTime().toLocalDate();
            for(GrapeStorageModel storageDay : grapeStorage) {
                /*if(storageDay.getCreated_at().toLocalDateTime().toLocalDate().equals(date))
                    continue;*/
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.View.REFERENCEGRAPESTORAGEROW_VIEW));
                AnchorPane userRow = loader.load();
                ReferencesGrapeStorageRowController controller = loader.getController();

                controller.idLabel.setText(String.valueOf(storageDay.getId()));
                controller.dateLabel.setText(String.valueOf(storageDay.getCreated_at()));
                controller.quantityLabel.setText(String.valueOf(storageDay.getQuantity()));
                controller.quantityOldLabel.setText(String.valueOf(storageDay.getQuantity_old()));
                date = storageDay.getCreated_at().toLocalDateTime().toLocalDate();
                if(storageDay.getDifference() > 0) {
                    controller.operationLabel.setText("Loading (+" + storageDay.getDifference() + ")");
                    controller.operationLabel.getStyleClass().add("text-info");
                } else {
                    controller.operationLabel.setText("Diluting (" + storageDay.getDifference() + ")");
                    controller.operationLabel.getStyleClass().add("text-error");
                }

                AnchorPane.setRightAnchor(userRow, 0.0);
                AnchorPane.setLeftAnchor(userRow, 0.0);
                userRow.setLayoutY(y);
                if(bg)
                    userRow.setStyle("-fx-background-color: rgba(66,65,54,0.21);");

                tableAnchorPane.getChildren().add(userRow);
                y+=45;
                bg=!bg;
            }

            if(grapeStorage.size() == 0) {
                errorGridPane.setVisible(true);
            }
        } catch (Exception ex) {
            log.error("User table reload error: " + ex.getMessage());
        }
    }
}
