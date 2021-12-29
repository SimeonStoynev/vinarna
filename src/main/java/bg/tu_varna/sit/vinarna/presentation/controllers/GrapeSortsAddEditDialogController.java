package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.GrapeCategoryService;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeCategoryModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class GrapeSortsAddEditDialogController {

    GrapeCategoryService grapeCategoryService = GrapeCategoryService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    Label sortNameErrorLabel;

    @FXML
    Label grapeTypeErrorLabel;

    @FXML
    ComboBox grapeCategoriesComboBox;

    @FXML
    Button submitButton;

    ObservableList<GrapeCategoryModel> grapeCategories;

    @FXML
    private void initialize() {
        errorLabelsClear();

        mainAnchorPanel.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                submitButton.fire();
                keyEvent.consume();
            }
        });

        grapeCategories = grapeCategoryService.getAll();
        grapeCategoriesComboBox.getItems().addAll(grapeCategories);
    }

    public void submitForm() {

    }

    private void errorLabelsClear() {
        sortNameErrorLabel.setText("");
        grapeTypeErrorLabel.setText("");
    }
}
