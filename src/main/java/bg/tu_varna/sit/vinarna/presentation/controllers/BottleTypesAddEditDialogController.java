package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.BottleStorageService;
import bg.tu_varna.sit.vinarna.business.BottleTypeService;
import bg.tu_varna.sit.vinarna.presentation.models.BottleTypeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.math3.util.Precision;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class BottleTypesAddEditDialogController {

    BottleTypeService bottleTypeService = BottleTypeService.getInstance();
    BottleStorageService bottleStorageService = BottleStorageService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    Label titleLabel;

    @FXML
    TextField bottleCapacityTextField;

    @FXML
    Label bottleCapacityErrorLabel;

    @FXML
    Button submitButton;

    BottleTypeModel bottleType;
    int action = 0;

    @FXML
    public void initialize() {
        errorLabelsClear();

        mainAnchorPanel.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                submitButton.fire();
                keyEvent.consume();
            }
        });
    }

    public void formInit(BottleTypeModel... bottleType) {
        if(bottleType.length == 0) {
            titleLabel.setText("Add bottle type");
            submitButton.setText("Add bottle");
            this.bottleType = new BottleTypeModel();
        } else {
            action = 1;
            titleLabel.setText("Edit bottle type");
            submitButton.setText("Edit bottle");
            this.bottleType = bottleType[0];

            bottleCapacityTextField.setText(String.format(Locale.US, "%.3f",this.bottleType.getCapacity()));
        }
    }

    public void submitForm() {
        errorLabelsClear();

        if(formValidate()) {
            String bottleCapacity = bottleCapacityTextField.getText();

            Double capacity = Double.parseDouble(bottleCapacity);
            capacity = Precision.round(capacity, 3);
            this.bottleType.setCapacity(capacity);

            if(this.action == 0) {
                this.bottleType.setId(0);
                Date date = new Date();
                this.bottleType.setCreated_at(new Timestamp(date.getTime()));
                this.bottleType.setUpdated_at(new Timestamp(date.getTime()));
                bottleTypeService.addBottleType(this.bottleType);

                Stage stage = (Stage) mainAnchorPanel.getScene().getWindow();
                stage.hide();
            } else {
                bottleTypeService.updateBottleType(this.bottleType);

                Stage stage = (Stage) mainAnchorPanel.getScene().getWindow();
                stage.hide();
            }
        }
    }

    private boolean formValidate() {
        boolean valid = true;
        String bottleCapacity = bottleCapacityTextField.getText();

        if(bottleCapacity.length() == 0) {
            bottleCapacityErrorLabel.setText("The field can not be empty.");
            valid = false;
        } else {
            try {
                Double capacity = Double.parseDouble(bottleCapacity);
                capacity = Precision.round(capacity, 3);
                if(capacity <= 0) {
                    bottleCapacityErrorLabel.setText("Please enter a positive number.");
                    valid = false;
                } else if(capacity > 999999) {
                    bottleCapacityErrorLabel.setText("Please enter a number less than 999999.");
                    valid = false;
                } else if(this.action == 0 && bottleTypeService.isBottleCapacityExists(capacity)) {
                    bottleCapacityErrorLabel.setText("A bottle with this capacity already exists.");
                    valid = false;
                } else if(this.action == 1 && !Objects.equals(this.bottleType.getCapacity(), capacity) && bottleTypeService.isBottleCapacityExists(capacity)) {
                    bottleCapacityErrorLabel.setText("A bottle with this capacity already exists.");
                    valid = false;
                } else if(this.action == 1 && bottleStorageService.getAllByBottle(this.bottleType).size() != 0) {
                    bottleCapacityErrorLabel.setText("You can not edit the bottle type. There are bottles of this type in the storage.");
                    valid = false;
                }
            } catch (Exception ex) {
                bottleCapacityErrorLabel.setText("Please enter a valid number. The decimal separator must be a decimal point.");
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
        bottleCapacityErrorLabel.setText("");
    }
}
