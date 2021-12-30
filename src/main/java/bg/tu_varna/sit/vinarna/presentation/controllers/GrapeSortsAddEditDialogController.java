package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.GrapeCategoryService;
import bg.tu_varna.sit.vinarna.business.GrapeSortService;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeCategoryModel;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.util.Date;

public class GrapeSortsAddEditDialogController {

    GrapeSortService grapeSortService = GrapeSortService.getInstance();
    GrapeCategoryService grapeCategoryService = GrapeCategoryService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    Label sortNameErrorLabel;

    @FXML
    Label grapeTypeErrorLabel;

    @FXML
    TextField sortNameTextField;

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
        errorLabelsClear();

        if(formValidate()) {
            String sortName = sortNameTextField.getText();
            GrapeCategoryModel grapeCategory = (GrapeCategoryModel) grapeCategoriesComboBox.getValue();

            GrapeSortModel sortModel = new GrapeSortModel();
            sortModel.setId(0);
            sortModel.setName(sortName);
            sortModel.setCategory(grapeCategory);

            Date date = new Date();
            sortModel.setCreated_at(new Timestamp(date.getTime()));
            sortModel.setUpdated_at(new Timestamp(date.getTime()));

            grapeSortService.grapeSortAdd(sortModel);

            Stage stage = (Stage) mainAnchorPanel.getScene().getWindow();
            stage.hide();
        }





    }

    public boolean formValidate() {
        boolean valid = true;
        String sortName = sortNameTextField.getText();
        GrapeCategoryModel grapeCategory = (GrapeCategoryModel) grapeCategoriesComboBox.getValue();

        if(sortName.length() == 0) {
            sortNameErrorLabel.setText("You must enter a sort name.");
            valid = false;
        } else if(grapeSortService.isSortNameExists(sortName)) {
            sortNameErrorLabel.setText("There is already a grape sort with this name.");
            valid = false;
        }

        if(grapeCategory == null) {
            grapeTypeErrorLabel.setText("Grape category is not selected.");
            valid = false;
        }

        return valid;
    }

    private void errorLabelsClear() {
        sortNameErrorLabel.setText("");
        grapeTypeErrorLabel.setText("");
    }
}
