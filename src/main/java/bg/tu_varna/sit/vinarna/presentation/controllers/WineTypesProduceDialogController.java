package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.WineRecipeService;
import bg.tu_varna.sit.vinarna.business.WineTypeService;
import bg.tu_varna.sit.vinarna.presentation.models.WineRecipeModel;
import bg.tu_varna.sit.vinarna.presentation.models.WineTypeModel;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.commons.math3.util.Precision;
import org.apache.log4j.Logger;

public class WineTypesProduceDialogController {
    private static final Logger log = Logger.getLogger(UsersAnchorPaneController.class);

    WineTypeService wineTypeService = WineTypeService.getInstance();
    WineRecipeService wineRecipeService = WineRecipeService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    TextField wineTypeTextField;

    @FXML
    TextField recipeApplyTextField;

    @FXML
    Label producedLitersLabel;

    @FXML
    Label wineTypeErrorLabel;

    @FXML
    Label quantityErrorLabel;


    WineTypeModel wineType;
    ObservableList<WineRecipeModel> wineRecipes;
    Double wineLiters;
    int multiply = 1;

    @FXML
    public void initialize() {
        errorLabelsClear();

        recipeApplyTextField.setTextFormatter(new TextFormatter<>((change) -> {
            String text = change.getControlNewText();
            if (text.matches("\\d*")) {
                return change;
            } else {
                return null;
            }
        }));

        recipeApplyTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                String quantityS = recipeApplyTextField.getText();
                quantityErrorLabel.setText("");

                if(quantityS.length() == 0) {
                    multiply = 0;
                } else {
                    try {
                        int quantity = Integer.parseInt(quantityS);
                        multiply = quantity;
                        if(quantity > 10) {
                            quantityErrorLabel.setText("The quantity must not be greater than 10.");
                        }

                    } catch (Exception ignored) {}
                }

                wineLitersCalculate();
                t.consume();
            }
        });
    }

    public void formInit(WineTypeModel wineType) {
        this.wineType = wineType;
        this.wineRecipes = wineRecipeService.getAllRecipesByWineType(wineType);
        this.wineLiters = 0.0;

        wineTypeTextField.setDisable(true);
        wineTypeTextField.setText(this.wineType.getName());

        wineLitersCalculate();

    }

    private void wineLitersCalculate() {
        this.wineLiters = 0.0;
        for(WineRecipeModel wineRecipe : this.wineRecipes) {
            Double liters = wineRecipe.getQuantity() * wineRecipe.getGrape_sort_id().getWine_liters();
            this.wineLiters += liters;
        }
        producedLitersLabel.setText(String.valueOf(Precision.round(this.wineLiters*multiply, 3)) + " L");
    }

    public void submitForm() {
        errorLabelsClear();

        if(formValidate()) {
            Double newProduced = this.wineType.getProduced() + (this.wineLiters * this.multiply);
            newProduced = Precision.round(newProduced, 3);
            this.wineType.setProduced(newProduced);

            wineTypeService.updateWineType(this.wineType);

            cancelButtonAction();
        }
    }

    private boolean formValidate() {
        boolean valid = true;
        String quantityS = recipeApplyTextField.getText();

        if(quantityS.length() == 0) {
            quantityErrorLabel.setText("The field can not be empty.");
            valid = false;
        } else {
            try {
                int quantity = Integer.parseInt(quantityS);
                multiply = quantity;
                if(quantity > 10) {
                    quantityErrorLabel.setText("The quantity must not be greater than 10.");
                    valid = false;
                }

            } catch (Exception ex) {
                quantityErrorLabel.setText("The quantity must be an integer.");
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
        wineTypeErrorLabel.setText("");
        quantityErrorLabel.setText("");
    }
}
