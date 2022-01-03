package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.*;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.presentation.models.*;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.math3.util.Precision;

import java.sql.Timestamp;
import java.util.Date;

public class BottlingWineDialogController {

    BottleTypeService bottleTypeService = BottleTypeService.getInstance();
    BottleStorageService bottleStorageService = BottleStorageService.getInstance();
    BottledWineStorageService bottledWineStorageService = BottledWineStorageService.getInstance();
    WineTypeService wineTypeService = WineTypeService.getInstance();
    NotificationService notificationService = NotificationService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    TextField wineTypeTextField;

    @FXML
    Label producedLitersLabel;

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

    @FXML
    Label availableBottlesLabel;

    @FXML
    Label commonErrorLabel;

    ObservableList<BottleTypeModel> bottleTypes;
    ObservableList<BottleStorageModel> bottleStorage;

    WineTypeModel wineType;

    @FXML
    public void initialize() {
        errorLabelsClear();
        availableBottlesLabel.setText("0 pic.");

        bottleTypeComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            BottleTypeModel selectedBottle = (BottleTypeModel)bottleTypeComboBox.getValue();
            if(selectedBottle != null) {
                BottleStorageModel bottleQuantity = bottleStorage.stream()
                        .filter(storage -> selectedBottle.getId() == storage.getBottle_type_id().getId())
                        .findFirst()
                        .orElse(null);
                if(bottleQuantity != null) {
                    availableBottlesLabel.setText(String.valueOf(bottleQuantity.getQuantity()) + " pics.");
                } else {
                    availableBottlesLabel.setText("0 pic.");
                }
            }
        });

        mainAnchorPanel.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                submitButton.fire();
                keyEvent.consume();
            }
        });

        bottlesQuantityTextField.setTextFormatter(new TextFormatter<>((change) -> {
            String text = change.getControlNewText();
            if (text.matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        }));

        bottlesQuantityTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                String quantityS = bottlesQuantityTextField.getText();
                bottleQuantityErrorLabel.setText("");

                if(quantityS.length() == 0) {
                    bottleQuantityErrorLabel.setText("The field can not be empty.");
                } else {
                    try {
                        int quantity = Integer.parseInt(quantityS);

                        BottleTypeModel selectedBottle = (BottleTypeModel)bottleTypeComboBox.getValue();
                        if(selectedBottle != null) {
                            BottleStorageModel bottleQuantity = bottleStorage.stream()
                                    .filter(storage -> selectedBottle.getId() == storage.getBottle_type_id().getId())
                                    .findFirst()
                                    .orElse(null);
                            if(bottleQuantity != null) {
                                if(bottleQuantity.getQuantity() < quantity) {
                                    bottleQuantityErrorLabel.setText("There are not enough available bottles in the storage.");
                                }
                            } else {
                                bottleQuantityErrorLabel.setText("There are not enough available bottles in the storage.");
                            }
                        }

                    } catch (Exception ignored) {}
                }

                t.consume();
            }
        });
    }

    public void formInit(WineTypeModel wineType) {
        this.wineType = wineType;
        this.bottleTypes = bottleTypeService.getAll();
        this.bottleStorage = bottleStorageService.getLatestAll();

        wineTypeTextField.setText(this.wineType.getName());
        wineTypeTextField.setDisable(true);
        producedLitersLabel.setText(String.valueOf(Precision.round(this.wineType.getProduced(), 3)) + " L");
        bottleTypeComboBox.getItems().addAll(this.bottleTypes);
    }

    public void submitForm() {
        errorLabelsClear();

        if(formValidate()) {
            String quantityS = bottlesQuantityTextField.getText();
            BottleTypeModel selectedBottle = (BottleTypeModel)bottleTypeComboBox.getValue();
            int quantity = Integer.parseInt(quantityS);
            Double neededWineQuantity = quantity * selectedBottle.getCapacity();
            Date date = new Date();

            BottleStorageModel bottleQuantity = bottleStorage.stream()
                    .filter(storage -> selectedBottle.getId() == storage.getBottle_type_id().getId())
                    .findFirst()
                    .orElse(null);
            int oldBottleQuantity = bottleQuantity.getQuantity();
            int newBottleQuantity = bottleQuantity.getQuantity() - quantity;
            int bottleQuantityDifference = -(quantity);

            BottleStorageModel newBottleStorage = new BottleStorageModel();
            newBottleStorage.setId(0);
            newBottleStorage.setBottle_type_id(selectedBottle);
            newBottleStorage.setQuantity(newBottleQuantity);
            newBottleStorage.setQuantity_old(oldBottleQuantity);
            newBottleStorage.setDifference(bottleQuantityDifference);
            newBottleStorage.setCreated_at(new Timestamp(date.getTime()));
            newBottleStorage.setUpdated_at(new Timestamp(date.getTime()));
            bottleStorageService.addStorage(newBottleStorage);

            if(oldBottleQuantity >= Constants.Minima.BOTTLES_MINIMUM && newBottleQuantity < Constants.Minima.BOTTLES_MINIMUM) {
                if(newBottleQuantity == 0)
                    notificationService.notifyUsers("The bottle " + newBottleStorage.getBottle_type_id().getCapacity() + " is out of stock.");
                else
                    notificationService.notifyUsers("The bottle " + newBottleStorage.getBottle_type_id().getCapacity() + " is below the minimum.");
            }

            BottledWineStorageModel oldBottledWine = bottledWineStorageService.getLastByBottleAndWine(selectedBottle, this.wineType);

            BottledWineStorageModel newBottledWineStorage = new BottledWineStorageModel();
            newBottledWineStorage.setId(0);
            newBottledWineStorage.setBottle_type_id(selectedBottle);
            newBottledWineStorage.setWine_type_id(this.wineType);
            if(oldBottledWine == null) {
                newBottledWineStorage.setQuantity_old(0);
                newBottledWineStorage.setQuantity(quantity);
            } else {
                newBottledWineStorage.setQuantity_old(oldBottledWine.getQuantity());
                newBottledWineStorage.setQuantity(oldBottledWine.getQuantity() + quantity);
            }
            newBottledWineStorage.setDifference(quantity);
            newBottledWineStorage.setCreated_at(new Timestamp(date.getTime()));
            newBottledWineStorage.setUpdated_at(new Timestamp(date.getTime()));
            bottledWineStorageService.addStorage(newBottledWineStorage);

            this.wineType.setProduced(this.wineType.getProduced() - neededWineQuantity);
            wineTypeService.updateWineType(this.wineType);

            cancelButtonAction();
        }
    }

    private boolean formValidate() {
        boolean valid = true;

        String quantityS = bottlesQuantityTextField.getText();
        BottleTypeModel selectedBottle = (BottleTypeModel)bottleTypeComboBox.getValue();
        int quantity = 0;
        Double neededWineQuantity = 0.0;

        if(quantityS.length() == 0) {
            bottleQuantityErrorLabel.setText("The field can not be empty.");
            valid = false;
        } else {
            try {
                quantity = Integer.parseInt(quantityS);

                if(quantity == 0) {
                    bottleQuantityErrorLabel.setText("Please enter a positive integer.");
                    valid = false;
                }

            } catch (Exception ex) {
                bottleQuantityErrorLabel.setText("Please enter integer.");
                valid = false;
            }
        }

        if(selectedBottle != null) {
            BottleStorageModel bottleQuantity = bottleStorage.stream()
                    .filter(storage -> selectedBottle.getId() == storage.getBottle_type_id().getId())
                    .findFirst()
                    .orElse(null);

            if(bottleQuantity != null) {
                if(bottleQuantity.getQuantity() == 0 || bottleQuantity.getQuantity() < quantity) {
                    bottleQuantityErrorLabel.setText("There are not enough available bottles in the storage.");
                    valid = false;
                }
            } else {
                bottleQuantityErrorLabel.setText("There are not enough available bottles in the storage.");
                valid = false;
            }

            neededWineQuantity = quantity * selectedBottle.getCapacity();
            if(neededWineQuantity > this.wineType.getProduced()) {
                commonErrorLabel.setText("There is not enough wine in the storage.");
                valid = false;
            }
        } else {
            bottleTypeErrorLabel.setText("You must select a bottle type.");
            valid = false;
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
        commonErrorLabel.setText("");
    }
}