package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.GrapeSortService;
import bg.tu_varna.sit.vinarna.business.WineRecipeService;
import bg.tu_varna.sit.vinarna.business.WineTypeService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import bg.tu_varna.sit.vinarna.presentation.models.WineRecipeModel;
import bg.tu_varna.sit.vinarna.presentation.models.WineTypeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.math3.util.Precision;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;

public class WineTypesAddEditDialogController {
    private static final Logger log = Logger.getLogger(UsersAnchorPaneController.class);

    public final WineTypeService wineTypeService = WineTypeService.getInstance();
    public final GrapeSortService grapeSortService = GrapeSortService.getInstance();
    public final WineRecipeService wineRecipeService = WineRecipeService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    TextField wineTypeNameTextField;

    @FXML
    ComboBox grapeCategoriesComboBox;

    @FXML
    AnchorPane grapeTypeListAnchorPane;

    @FXML
    Label wineTypeNameErrorLabel;

    @FXML
    Label grapeTypeErrorLabel;

    @FXML
    Label grapeRowsErrorLabel;

    WineTypeModel wineType;
    ObservableList<GrapeSortModel> allGrapeSorts;
    ObservableList<GrapeSortModel> grapeSorts;
    ObservableList<WineRecipeModel> dbWineRecipes;
    ObservableList<WineRecipeModel> newWineRecipes;

    @FXML
    public void initialize() {
        errorLabelsClear();
        grapeTypeListAnchorPane.getChildren().clear();
        newWineRecipes = FXCollections.observableArrayList();
    }

    private void getGrapeSorts() {
        allGrapeSorts = grapeSortService.getAllSorts();
    }

    public void formInit(WineTypeModel... wineType) {
        getGrapeSorts();

        if(wineType.length == 0) {
            this.wineType = new WineTypeModel();
            grapeCategoriesComboBox.getItems().addAll(allGrapeSorts);
        } else {
            this.wineType = wineType[0];
            dbWineRecipes = wineRecipeService.getAllRecipesByWineType(this.wineType);

            wineTypeNameTextField.setText(this.wineType.getName());
            grapeCategoriesComboBox.getItems().addAll(grapeSorts);
        }

    }
    public void grapeSortAddAction() {
        grapeTypeErrorLabel.setText("");
        GrapeSortModel grapeSort = (GrapeSortModel) grapeCategoriesComboBox.getValue();

        if(grapeSort == null) {
            grapeTypeErrorLabel.setText("Grape sort must be selected.");
            return;
        }

        WineRecipeModel wineRecipe = new WineRecipeModel();
        wineRecipe.setId(0);
        wineRecipe.setWine_type_id(this.wineType);
        wineRecipe.setGrape_sort_id(grapeSort);
        wineRecipe.setQuantity(0.00);
        Date date = new Date();
        wineRecipe.setCreated_at(new Timestamp(date.getTime()));
        wineRecipe.setUpdated_at(new Timestamp(date.getTime()));
        newWineRecipes.add(wineRecipe);
        grapesTableReload();
        grapeSortsComboBoxReload();
    }

    private void grapeSortsComboBoxReload() {
        grapeCategoriesComboBox.getItems().clear();
        for(GrapeSortModel grapeSort : allGrapeSorts) {
            if(newWineRecipes.stream().filter(carnet -> grapeSort.equals(carnet.getGrape_sort_id())).findFirst().orElse(null) == null) {
                grapeCategoriesComboBox.getItems().add(grapeSort);
            }
        }
    }

    private void grapesTableReload() {
        try {
            grapeTypeListAnchorPane.getChildren().clear();
            int y = 0;
            boolean bg = false;
            for(WineRecipeModel wineRecipe : newWineRecipes) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.View.WINETYPESGRAPECHOSEROW_VIEW));
                AnchorPane wineRow = loader.load();
                WineTypeGrapeChoseRowController controller = loader.getController();

                controller.idLabel.setText(String.valueOf(wineRecipe.getGrape_sort_id().getId()));
                controller.grapeNameLabel.setText(wineRecipe.getGrape_sort_id().getName());
                if(wineRecipe.getQuantity() != 0)
                    controller.grapeQuantityTextField.setText(String.valueOf(wineRecipe.getQuantity()));
                controller.grapeQuantityTextField.setTextFormatter(new TextFormatter<>((change) -> {
                    String text = change.getControlNewText();
                    if (text.matches("\\d*\\.?\\d*")) {
                        return change;
                    } else {
                        return null;
                    }
                }));
                controller.grapeQuantityTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent t) {
                        String quantityS = controller.grapeQuantityTextField.getText();
                        try {
                            Double quantity = Double.parseDouble(quantityS);
                            wineRecipe.setQuantity(Precision.round(quantity, 2));
                        } catch (Exception ignored) {}
                        t.consume();
                    }
                });

                controller.grapeRemoveButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        newWineRecipes.remove(wineRecipe);
                        grapesTableReload();
                        grapeSortsComboBoxReload();
                    }
                });

                AnchorPane.setRightAnchor(wineRow, 0.0);
                AnchorPane.setLeftAnchor(wineRow, 0.0);
                wineRow.setLayoutY(y);
                if(bg)
                    wineRow.setStyle("-fx-background-color: rgba(66,65,54,0.21);");

                grapeTypeListAnchorPane.getChildren().add(wineRow);
                y+=50;
                bg=!bg;
            }
        } catch (Exception ex) {
            log.error("Grapes table table reload error: " + ex.getMessage());
        }
    }

    public void submitForm() {
        errorLabelsClear();

        if(addFormValidate()) {
            String wineName = wineTypeNameTextField.getText();
            this.wineType.setId(0);
            this.wineType.setName(wineName);

            Date date = new Date();
            this.wineType.setCreated_at(new Timestamp(date.getTime()));
            this.wineType.setUpdated_at(new Timestamp(date.getTime()));

            int insertId = wineTypeService.addWineType(this.wineType);
            if(insertId > 0) {
                for(WineRecipeModel wineRecipe : newWineRecipes) {
                    wineRecipe.getWine_type_id().setId(insertId);
                    wineRecipeService.addWineRecipe(wineRecipe);
                }
            }
        }
    }

    private boolean addFormValidate() {
        boolean valid = true;
        String wineName = wineTypeNameTextField.getText();

        if(wineName.length() == 0) {
            wineTypeNameErrorLabel.setText("The field can not be empty.");
            valid = false;
        } else if(wineName.length() > 100) {
            wineTypeNameErrorLabel.setText("The field can contain only 100 chars.");
            valid = false;
        } else if(wineTypeService.isWineTypeNameExists(wineName)) {
            wineTypeNameErrorLabel.setText("There is already wine with this name.");
            valid = false;
        }

        if(newWineRecipes.size() == 0) {
            grapeRowsErrorLabel.setText("You must select at least one grape sort.");
            valid = false;
        } else {
            for(WineRecipeModel wineRecipe : newWineRecipes) {
                if(wineRecipe.getQuantity() == 0) {
                    grapeRowsErrorLabel.setText("You must fill all the quantities.");
                    valid = false;
                }
            }
        }

        return valid;
    }

    public void errorLabelsClear() {
        wineTypeNameErrorLabel.setText("");
        grapeTypeErrorLabel.setText("");
        grapeRowsErrorLabel.setText("");
    }
}
