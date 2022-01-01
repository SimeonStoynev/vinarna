package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.BottleStorageService;
import bg.tu_varna.sit.vinarna.business.BottleTypeService;
import bg.tu_varna.sit.vinarna.presentation.models.BottleStorageModel;
import bg.tu_varna.sit.vinarna.presentation.models.BottleTypeModel;
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

public class BottleQuantityAddDialogController {

    public final BottleTypeService bottleTypeService = BottleTypeService.getInstance();
    public final BottleStorageService bottleStorageService = BottleStorageService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    ComboBox bottleTypeComboBox;

    @FXML
    TextField bottleQuantityTextField;

    @FXML
    Button submitButton;

    @FXML
    Label bottleTypeErrorLabel;

    @FXML
    Label bottleQuantityErrorLabel;

    ObservableList<BottleTypeModel> dbBottleTypes;
    BottleTypeModel bottleType;
    int action = 0;


    @FXML
    public void initialize() {
        errorLabelsClear();

        this.dbBottleTypes = bottleTypeService.getAll();
        this.bottleTypeComboBox.getItems().addAll(this.dbBottleTypes);

        mainAnchorPanel.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                submitButton.fire();
                keyEvent.consume();
            }
        });
    }

    public void formInit(BottleTypeModel... bottleType) {
        if(bottleType.length == 0) {
            this.bottleType = new BottleTypeModel();
            this.bottleType.setId(0);
        } else {
            this.action = 1;
            this.bottleType = bottleType[0];
            this.bottleTypeComboBox.setValue(bottleType[0]);
        }
    }

    public void submitForm() {
        errorLabelsClear();

        if(formValidate()) {
            this.bottleType = (BottleTypeModel) this.bottleTypeComboBox.getValue();
            String bottleQuantityS = this.bottleQuantityTextField.getText();
            int bottleQuantity = Integer.parseInt(bottleQuantityS);

            BottleStorageModel bottleStorageOld = bottleStorageService.getLastByBottle(bottleType);
            BottleStorageModel bottleStorageNew = new BottleStorageModel();

            int oldQuantity = 0;
            int newQuantity = bottleQuantity;
            int quantityDifference = newQuantity;

            if(bottleStorageOld != null) {
                newQuantity = bottleStorageOld.getQuantity() + bottleQuantity;
                quantityDifference = newQuantity - bottleStorageOld.getQuantity();
                oldQuantity = bottleStorageOld.getQuantity();
            }

            bottleStorageNew.setId(0);
            bottleStorageNew.setQuantity_old(oldQuantity);
            bottleStorageNew.setQuantity(newQuantity);
            bottleStorageNew.setDifference(quantityDifference);
            bottleStorageNew.setBottle_type_id(this.bottleType);

            Date date = new Date();
            bottleStorageNew.setCreated_at(new Timestamp(date.getTime()));
            bottleStorageNew.setUpdated_at(new Timestamp(date.getTime()));

            this.bottleStorageService.addStorage(bottleStorageNew);

            Stage stage = (Stage) mainAnchorPanel.getScene().getWindow();
            stage.hide();

        }
    }

    public boolean formValidate() {
        boolean valid = true;
        BottleTypeModel bottleType = (BottleTypeModel) this.bottleTypeComboBox.getValue();
        String bottleQuantity = this.bottleQuantityTextField.getText();

        if(bottleType == null) {
            bottleTypeErrorLabel.setText("Bottle type is not selected.");
            valid = false;
        }

        if(bottleQuantity.length() == 0) {
            bottleQuantityErrorLabel.setText("This field can not be empty.");
            valid = false;
        } else {
            try{
                int num = Integer.parseInt(bottleQuantity);
                if(num < 0) {
                    bottleQuantityErrorLabel.setText("Please enter a positive number.");
                    valid = false;
                } else if(num > 999999) {
                    bottleQuantityErrorLabel.setText("Please enter a number less than 999999.");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                bottleQuantityErrorLabel.setText("Please enter a valid integer.");
                valid = false;
            }
        }

        return valid;
    }

    public void cancelButtonAction() {
        Stage stage = (Stage) mainAnchorPanel.getScene().getWindow();
        stage.hide();
    }

    private void errorLabelsClear() {
        bottleTypeErrorLabel.setText("");
        bottleQuantityErrorLabel.setText("");
    }
}
