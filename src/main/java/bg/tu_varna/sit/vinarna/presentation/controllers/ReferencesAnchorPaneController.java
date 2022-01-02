package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.BottleTypeService;
import bg.tu_varna.sit.vinarna.business.GrapeSortService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.common.ViewsManager;
import bg.tu_varna.sit.vinarna.presentation.models.BottleTypeModel;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class ReferencesAnchorPaneController {

    GrapeSortService grapeStorageService = GrapeSortService.getInstance();
    BottleTypeService bottleTypeService = BottleTypeService.getInstance();

    @FXML
    ComboBox referenceTypeComboBox;

    @FXML
    DatePicker firstDateDatePicker;

    @FXML
    DatePicker lastDateDatePicker;

    @FXML
    ComboBox referenceByComboBox;

    @FXML
    Label referenceByLabel;

    @FXML
    GridPane errorGridPane;

    @FXML
    AnchorPane tableAnchorPane;

    @FXML
    public void initialize() {
        referenceTypeComboBox.getItems().add("Grape sorts quantity");
        referenceTypeComboBox.getItems().add("Bottle types quantity");
        referenceTypeComboBox.getItems().add("Bottled wines quantity");

        firstDateDatePicker.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });

        lastDateDatePicker.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });

        referenceTypeComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if(newValue.equals("Grape sorts quantity") || newValue.equals("Bottled wines quantity")) {
                referenceByLabel.setText("Select grape sort");
                referenceByComboBox.setPromptText("Select grape sort");
                referenceByComboBox.getItems().setAll(grapeStorageService.getAllSorts());

            } else if(newValue.equals("Bottle types quantity")) {
                referenceByLabel.setText("Select bottle type");
                referenceByComboBox.setPromptText("Select bottle type");
                referenceByComboBox.getItems().setAll(bottleTypeService.getAll());
            }
        });
    }

    public void submitButton() throws IOException {
        errorGridPane.setVisible(false);
        if(formValidate()) {

            LocalDate startDate = firstDateDatePicker.getValue();
            LocalDate endDate = lastDateDatePicker.getValue();

            if(referenceTypeComboBox.getValue().equals("Grape sorts quantity")) {
                GrapeSortModel grapeSort = (GrapeSortModel) referenceByComboBox.getValue();

                FXMLLoader loader = new FXMLLoader(ReferencesGrapeAnchorPaneController.class.getResource(Constants.View.REFERENCEGRAPEANCHORPANE_VIEW));
                AnchorPane sp = loader.load();

                ReferencesGrapeAnchorPaneController controller = loader.getController();
                controller.init(grapeSort, startDate, endDate);

                ViewsManager.loadAnchorPane(sp, tableAnchorPane);

            } else if(referenceTypeComboBox.getValue().equals("Bottle types quantity")) {
                BottleTypeModel bottleType = (BottleTypeModel) referenceByComboBox.getValue();

                FXMLLoader loader = new FXMLLoader(ReferencesBottleAnchorPaneController.class.getResource(Constants.View.REFERENCEBOTTLEANCHORPANE_VIEW));
                AnchorPane sp = loader.load();

                ReferencesBottleAnchorPaneController controller = loader.getController();
                controller.init(bottleType, startDate, endDate);

                ViewsManager.loadAnchorPane(sp, tableAnchorPane);

            } else if(referenceTypeComboBox.getValue().equals("Bottled wines quantity")) {
                GrapeSortModel grapeSort = (GrapeSortModel) referenceByComboBox.getValue();
            }

        } else {
            tableAnchorPane.getChildren().clear();
            errorGridPane.setVisible(true);
        }
    }

    private boolean formValidate() {
        boolean valid = true;

        try {
            if(referenceTypeComboBox.getValue() != null) {
                if(referenceTypeComboBox.getValue().equals("Grape sorts quantity") || referenceTypeComboBox.getValue().equals("Bottled wines quantity")) {
                    GrapeSortModel grapeSort = (GrapeSortModel) referenceByComboBox.getValue();
                    if(grapeSort == null)
                        valid = false;

                } else if(referenceTypeComboBox.getValue().equals("Bottle types quantity")) {
                    BottleTypeModel bottleType = (BottleTypeModel) referenceByComboBox.getValue();
                    if(bottleType == null)
                        valid = false;
                }

                LocalDate startDate = firstDateDatePicker.getValue();
                LocalDate endDate = lastDateDatePicker.getValue();

                if(startDate == null || endDate == null) {
                    valid = false;
                }
            } else {
                valid = false;
            }
        } catch (Exception ex) {
            valid = false;
        }



        return valid;
    }
}
