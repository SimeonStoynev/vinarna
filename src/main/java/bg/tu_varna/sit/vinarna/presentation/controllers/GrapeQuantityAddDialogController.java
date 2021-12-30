package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.GrapeSortService;
import bg.tu_varna.sit.vinarna.business.GrapeStorageService;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeStorageModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.math3.util.Precision;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;

public class GrapeQuantityAddDialogController {

    public final GrapeSortService grapeSortService = GrapeSortService.getInstance();
    public final GrapeStorageService grapeStorageService = GrapeStorageService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    ComboBox grapeSortComboBox;

    @FXML
    TextField grapeQuantityTextField;

    @FXML
    Button submitButton;

    @FXML
    Label grapeSortErrorLabel;

    @FXML
    Label grapeQuantityErrorLabel;

    ObservableList<GrapeSortModel> grapeSorts;

    @FXML
    private void initialize() {
        errorLabelsClear();

        grapeSorts = grapeSortService.getAllSorts();
        grapeSortComboBox.getItems().addAll(grapeSorts);
    }

    public void formInit(GrapeSortModel... grapeSort) {
        if(grapeSort.length > 0) {
            grapeSortComboBox.setValue(grapeSort[0]);
        }
    }

    public void submitForm() {
        errorLabelsClear();

        if(formValidate()) {
            GrapeSortModel grapeSort = (GrapeSortModel)grapeSortComboBox.getValue();
            String grapeQuantityS = grapeQuantityTextField.getText();
            Double grapeQuantity = Double.valueOf(grapeQuantityS);
            grapeQuantity = Precision.round(grapeQuantity, 2);

            GrapeStorageModel grapeStorageOld = grapeStorageService.getLastRowByGrapeSortId(grapeSort.getId());
            GrapeStorageModel grapeStorageNew = new GrapeStorageModel();

            Double oldQuantity = 0.0;
            Double newQuantity = grapeQuantity;
            Double quantityDifference = newQuantity;

            if(grapeStorageOld != null) {
                newQuantity = grapeStorageOld.getQuantity() + grapeQuantity;
                quantityDifference = newQuantity - grapeStorageOld.getQuantity();
                newQuantity = Precision.round(newQuantity, 2);
                quantityDifference = Precision.round(quantityDifference, 2);
                oldQuantity = grapeStorageOld.getQuantity();
            }

            grapeStorageNew.setId(0);
            grapeStorageNew.setQuantity_old(oldQuantity);
            grapeStorageNew.setQuantity(newQuantity);
            grapeStorageNew.setDifference(quantityDifference);
            grapeStorageNew.setSort(grapeSort);

            Date date = new Date();
            grapeStorageNew.setCreated_at(new Timestamp(date.getTime()));
            grapeStorageNew.setUpdated_at(new Timestamp(date.getTime()));

            grapeStorageService.addStorage(grapeStorageNew);

            Stage stage = (Stage) mainAnchorPanel.getScene().getWindow();
            stage.hide();
        }
    }

    private boolean formValidate() {
        boolean valid = true;
        GrapeSortModel grapeSort = (GrapeSortModel)grapeSortComboBox.getValue();
        String grapeQuantity = grapeQuantityTextField.getText();

        if(grapeSort == null) {
            grapeSortErrorLabel.setText("Grape sort is not selected.");
            valid = false;
        }

        if(grapeQuantity.length() == 0) {
            grapeQuantityErrorLabel.setText("The field can not be empty.");
            valid = false;
        } else {
            try {
                Double quantity = Double.parseDouble(grapeQuantity);
                if(quantity <= 0) {
                    grapeQuantityErrorLabel.setText("Please enter a positive number.");
                    valid = false;
                } else if(quantity > 999999) {
                    grapeQuantityErrorLabel.setText("Please enter a number less than 999999.");
                    valid = false;
                }
            } catch (Exception ex) {
                grapeQuantityErrorLabel.setText("Please enter a valid number. The decimal separator must be a decimal point.");
                valid = false;
            }
        }


        return valid;
    }

    private void errorLabelsClear() {
        grapeSortErrorLabel.setText("");
        grapeQuantityErrorLabel.setText("");
    }
}
