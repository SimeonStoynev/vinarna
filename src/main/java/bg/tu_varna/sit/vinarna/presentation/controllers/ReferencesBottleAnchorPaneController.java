package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.BottleStorageService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.presentation.models.BottleStorageModel;
import bg.tu_varna.sit.vinarna.presentation.models.BottleTypeModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ReferencesBottleAnchorPaneController {
    private static final Logger log = Logger.getLogger(UsersAnchorPaneController.class);

    BottleStorageService bottleStorageService = BottleStorageService.getInstance();

    @FXML
    AnchorPane tableAnchorPane;

    @FXML
    GridPane errorGridPane;

    BottleTypeModel bottleType;
    LocalDate startDate;
    LocalDate endDate;

    ObservableList<BottleStorageModel> bottleStorage;

    @FXML
    public void initialize() {
        errorGridPane.setVisible(false);
    }

    public void init(BottleTypeModel bottleType, LocalDate startDate, LocalDate endDate) {
        this.bottleType = bottleType;
        this.startDate = startDate;
        this.endDate = endDate;

        this.bottleStorage = bottleStorageService.getAllByBottleAndPeriod(bottleType, startDate, endDate);

        tableViewReload();
    }

    public void tableViewReload() {
        try {
            tableAnchorPane.getChildren().clear();
            int y = 0;
            boolean bg = false;
            LocalDate date = new Timestamp(0).toLocalDateTime().toLocalDate();
            for(BottleStorageModel storageDay : bottleStorage) {
                /*if(storageDay.getCreated_at().toLocalDateTime().toLocalDate().equals(date))
                    continue;*/
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.View.REFERENCEBOTTLESTORAGEROW_VIEW));
                AnchorPane userRow = loader.load();
                ReferencesBottleStorageRowController controller = loader.getController();

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

            if(bottleStorage.size() == 0) {
                errorGridPane.setVisible(true);
            }
        } catch (Exception ex) {
            log.error("GrapeReference table load error: " + ex.getMessage());
        }
    }
}
