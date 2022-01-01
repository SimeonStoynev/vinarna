package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.presentation.models.BottleStorageModel;
import bg.tu_varna.sit.vinarna.presentation.models.BottleTypeModel;
import bg.tu_varna.sit.vinarna.presentation.models.WineTypeModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BottlingWineDialogController {

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    TextField wineTypeTextField;

    @FXML
    ComboBox bottleTypeComboBox;

    @FXML
    TextField bottlesQuantityTextField;

    @FXML
    Button submitButton;

    @FXML
    Label bottleTypeErrorLabel;

    @FXML
    Label bottleQuantityErrorLabel;

    ObservableList<BottleTypeModel> bottleTypes;
    ObservableList<BottleStorageModel> bottleStorage;

    WineTypeModel wineType;

    @FXML
    public void initialize() {

    }

    public void formInit(WineTypeModel wineType) {
        this.wineType = wineType;

    }

    public void submitForm() {

    }

    public void cancelButtonAction() {

    }
}