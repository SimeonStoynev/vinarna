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
import org.apache.commons.math3.util.Precision;

import java.sql.Timestamp;
import java.util.Date;

public class GrapeSortsAddEditDialogController {

    GrapeSortService grapeSortService = GrapeSortService.getInstance();
    GrapeCategoryService grapeCategoryService = GrapeCategoryService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    TextField sortNameTextField;

    @FXML
    ComboBox grapeCategoriesComboBox;

    @FXML
    TextField litersPerKgTextField;

    @FXML
    Button submitButton;

    @FXML
    Label sortNameErrorLabel;

    @FXML
    Label grapeTypeErrorLabel;

    @FXML
    Label litersPerKgTextFieldErrorLabel;

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
            String litersPerKgS = litersPerKgTextField.getText();
            Double litersPerKg = Precision.round(Double.parseDouble(litersPerKgS), 2);

            GrapeSortModel sortModel = new GrapeSortModel();
            sortModel.setId(0);
            sortModel.setName(sortName);
            sortModel.setCategory(grapeCategory);
            sortModel.setWine_liters(litersPerKg);

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
        String litersPerKgS = litersPerKgTextField.getText();

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

        if(litersPerKgS.length() == 0) {
            litersPerKgTextFieldErrorLabel.setText("The field can not be empty.");
            valid = false;
        } else {
            try {
                Double litersPerKg = Double.parseDouble(litersPerKgS);
                if(litersPerKg <= 0) {
                    litersPerKgTextFieldErrorLabel.setText("Please enter a positive number.");
                    valid = false;
                } else if(litersPerKg > 999999) {
                    litersPerKgTextFieldErrorLabel.setText("Please enter a number less than 999999.");
                    valid = false;
                }
            } catch (Exception ex) {
                litersPerKgTextFieldErrorLabel.setText("Please enter a valid number. The decimal separator must be a decimal point.");
                valid = false;
            }
        }

        return valid;
    }

    private void errorLabelsClear() {
        sortNameErrorLabel.setText("");
        grapeTypeErrorLabel.setText("");
        litersPerKgTextFieldErrorLabel.setText("");
    }
}
