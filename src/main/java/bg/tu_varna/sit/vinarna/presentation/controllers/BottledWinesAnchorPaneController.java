package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.BottledWineStorageService;
import bg.tu_varna.sit.vinarna.business.WineTypeService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.presentation.models.BottledWineStorageModel;
import bg.tu_varna.sit.vinarna.presentation.models.WineTypeModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.apache.log4j.Logger;

public class BottledWinesAnchorPaneController {
    private static final Logger log = Logger.getLogger(BottleTypesAnchorPaneController.class);

    WineTypeService wineTypeService = WineTypeService.getInstance();
    BottledWineStorageService bottledWineStorageService = BottledWineStorageService.getInstance();

    @FXML
    ComboBox wineTypeComboBox;

    @FXML
    AnchorPane bottledWinesRowsAnchorPane;

    @FXML
    GridPane errorGridPane;

    @FXML
    Label errorLabel;

    ObservableList<BottledWineStorageModel> bottledWines;

    @FXML
    public void initialize() {
        bottledWinesRowsAnchorPane.getChildren().clear();
        wineTypeComboBox.getItems().addAll(wineTypeService.getAllWineTypes());

        wineTypeComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            WineTypeModel selectedWine = (WineTypeModel)wineTypeComboBox.getValue();
            if(selectedWine != null) {
                tableReload(selectedWine);
            }
        });
    }

    private void tableReload(WineTypeModel wine) {
        errorGridPane.setVisible(false);
        try {
            this.bottledWines = bottledWineStorageService.getLatestAllByWine(wine);

            int y = 0;
            boolean bg = false;
            bottledWinesRowsAnchorPane.getChildren().clear();
            for(BottledWineStorageModel bottledWine : this.bottledWines) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.View.BOTTLEDWINESROW_VIEW));
                AnchorPane bottledWineRow = loader.load();
                BottledWinesRowController controller = loader.getController();

                controller.idLabel.setText(String.valueOf(bottledWine.getId()));
                controller.wineTypeLabel.setText(bottledWine.getWine_type_id().getName());
                controller.bottleTypeLabel.setText(String.format("%.3f",bottledWine.getBottle_type_id().getCapacity()) + " L");
                controller.quantityLabel.setText(String.valueOf(bottledWine.getQuantity()));

                AnchorPane.setRightAnchor(bottledWineRow, 0.0);
                AnchorPane.setLeftAnchor(bottledWineRow, 0.0);
                bottledWineRow.setLayoutY(y);
                if(bg)
                    bottledWineRow.setStyle("-fx-background-color: rgba(66,65,54,0.21);");

                bottledWinesRowsAnchorPane.getChildren().add(bottledWineRow);
                y+=45;
                bg=!bg;
            }

            if(this.bottledWines.size() == 0) {
                errorGridPane.setVisible(true);
                errorLabel.setText("There are no results.");
            }

        } catch (Exception ex) {
            log.error("BottleTypes table reload error: " + ex.getMessage());
        }
    }

}
