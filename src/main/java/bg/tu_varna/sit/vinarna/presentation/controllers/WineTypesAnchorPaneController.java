package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.WineTypeService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.presentation.models.WineTypeModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;

public class WineTypesAnchorPaneController {
    private static final Logger log = Logger.getLogger(UsersAnchorPaneController.class);

    WineTypeService wineTypeService = WineTypeService.getInstance();

    @FXML
    AnchorPane wineTypesRowsAnchorPane;

    ObservableList<WineTypeModel> wineTypes;

    @FXML
    public void initialize() {
        wineTypesTableViewReload();
    }

    public void getWineTypes() {
        wineTypes = wineTypeService.getAllWineTypes();
    }

    public void wineTypesTableViewReload() {
        try {
            getWineTypes();
            wineTypesRowsAnchorPane.getChildren().clear();
            int y = 0;
            boolean bg = false;
            for(WineTypeModel wineType : wineTypes) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.View.WINETYPESTABLEROW_VIEW));
                AnchorPane userRow = loader.load();
                WineTypesTableRowController controller = loader.getController();

                controller.idLabel.setText(String.valueOf(wineType.getId()));
                controller.wineTypeNameLabel.setText(wineType.getName());

                AnchorPane.setRightAnchor(userRow, 0.0);
                AnchorPane.setLeftAnchor(userRow, 0.0);
                userRow.setLayoutY(y);
                if(bg)
                    userRow.setStyle("-fx-background-color: rgba(66,65,54,0.21);");

                wineTypesRowsAnchorPane.getChildren().add(userRow);
                y+=45;
                bg=!bg;
            }
        } catch (Exception ex) {
            log.error("Wine types table reload error: " + ex.getMessage());
        }
    }

    public void wineTypeAddButton() {

    }
}
