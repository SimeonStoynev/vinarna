package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.BottleStorageService;
import bg.tu_varna.sit.vinarna.business.BottleTypeService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.presentation.models.BottleStorageModel;
import bg.tu_varna.sit.vinarna.presentation.models.BottleTypeModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.Collections;
import java.util.Objects;

public class BottleTypesAnchorPaneController {
    private static final Logger log = Logger.getLogger(BottleTypesAnchorPaneController.class);

    public final BottleTypeService bottleTypeService = BottleTypeService.getInstance();
    public final BottleStorageService bottleStorageService = BottleStorageService.getInstance();

    @FXML
    AnchorPane bottleRowsAnchorPane;

    ObservableList<BottleTypeModel> bottleTypes;
    ObservableList<BottleStorageModel> bottleStorage;

    @FXML
    public void initialize() {
        bottleTypesTableViewReload();
    }

    public void getBottleTypes() {
        bottleTypes = bottleTypeService.getAll();
        bottleStorage = bottleStorageService.getAll();
        Collections.reverse(bottleStorage);
    }

    public void bottleTypesTableViewReload() {
        try {
            getBottleTypes();
            bottleRowsAnchorPane.getChildren().clear();

            int y = 0;
            boolean bg = false;
            for(BottleTypeModel bottleType : this.bottleTypes) {
                BottleStorageModel quantity = this.bottleStorage.stream()
                        .filter(storage -> bottleType.getId() == storage.getBottle_type_id().getId())
                        .findFirst()
                        .orElse(null);

                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.View.BOTTLETYPESTABLEROW_VIEW));
                AnchorPane bottleRow = loader.load();
                BottleTypesTableRowController controller = loader.getController();

                controller.idLabel.setText(String.valueOf(bottleType.getId()));
                controller.bottleTypeCapacityLabel.setText(String.format("%.3f",bottleType.getCapacity()) + " L");

                if(quantity != null) {
                    controller.bottleTypeQuantityNameLabel.setText(String.valueOf(quantity.getQuantity()) + " pcs.");
                    if(quantity.getQuantity() < Constants.Minima.BOTTLES_MINIMUM)
                        controller.bottleTypeQuantityNameLabel.getStyleClass().add("text-error");
                } else {
                    controller.bottleTypeQuantityNameLabel.setText("0 pcs.");
                    controller.bottleTypeQuantityNameLabel.setDisable(true);
                }

                controller.addQuantityCustomMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        bottleQuantityAddDialogShow(bottleType);
                    }
                });

                controller.editCustomMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        bottleTypesAddEditDialogShow(bottleType);
                    }
                });

                AnchorPane.setRightAnchor(bottleRow, 0.0);
                AnchorPane.setLeftAnchor(bottleRow, 0.0);
                bottleRow.setLayoutY(y);
                if(bg)
                    bottleRow.setStyle("-fx-background-color: rgba(66,65,54,0.21);");

                bottleRowsAnchorPane.getChildren().add(bottleRow);
                y+=45;
                bg=!bg;
            }
        } catch (Exception ex) {
            log.error("BottleTypes table reload error: " + ex.getMessage());
        }
    }

    public void bottleTypeAddButton() {
        bottleTypesAddEditDialogShow();
    }

    public void bottleTypeTableViewReload() {
        bottleTypesTableViewReload();
    }

    public void bottleQuantityAddButton() {
        bottleQuantityAddDialogShow();
    }

    public void bottleTypesAddEditDialogShow(BottleTypeModel... bottleType) {
        Stage stage = (Stage) bottleRowsAnchorPane.getScene().getWindow();

        try {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);

            URL path = BottleTypesAddEditDialogController.class.getResource(Constants.View.BOTTLETYPESADDEDITDIALOG_VIEW);
            if (path != null) {
                FXMLLoader loader = new FXMLLoader(BottleTypesAddEditDialogController.class.getResource(Constants.View.BOTTLETYPESADDEDITDIALOG_VIEW));
                Parent root = loader.load();


                Scene scene = new Scene(root);
                dialog.getIcons().add(new Image(Objects.requireNonNull(BottleTypesAddEditDialogController.class.getResourceAsStream(Constants.Media.APP_ICON))));
                dialog.setScene(scene);
                dialog.setResizable(false);
                dialog.setTitle("Add Grape Quantity");

                BottleTypesAddEditDialogController controller = loader.getController();
                controller.formInit(bottleType);
                dialog.show();

                dialog.setOnHiding(new EventHandler<WindowEvent>(){
                    public void handle(WindowEvent we) {
                        dialog.close();
                        bottleTypesTableViewReload();
                    }

                });

            } else {
                log.error("Dialog couldn't be loaded: " + Constants.View.USERSADDEDITDIALOG_VIEW);
            }
        } catch (Exception ex) {
            log.error("Dialog couldn't be loaded: " + ex);
        }
    }

    public void bottleQuantityAddDialogShow(BottleTypeModel... bottleType) {
        Stage stage = (Stage) bottleRowsAnchorPane.getScene().getWindow();

        try {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);

            URL path = BottleQuantityAddDialogController.class.getResource(Constants.View.BOTTLEQUANTITYADDDIALOG_VIEW);
            if (path != null) {
                FXMLLoader loader = new FXMLLoader(BottleQuantityAddDialogController.class.getResource(Constants.View.BOTTLEQUANTITYADDDIALOG_VIEW));
                Parent root = loader.load();


                Scene scene = new Scene(root);
                dialog.getIcons().add(new Image(Objects.requireNonNull(BottleQuantityAddDialogController.class.getResourceAsStream(Constants.Media.APP_ICON))));
                dialog.setScene(scene);
                dialog.setResizable(false);
                dialog.setTitle("Add bottle quantity");

                BottleQuantityAddDialogController controller = loader.getController();
                controller.formInit(bottleType);
                dialog.show();

                dialog.setOnHiding(new EventHandler<WindowEvent>(){
                    public void handle(WindowEvent we) {
                        dialog.close();
                        bottleTypesTableViewReload();
                    }

                });

            } else {
                log.error("Dialog couldn't be loaded: " + Constants.View.USERSADDEDITDIALOG_VIEW);
            }
        } catch (Exception ex) {
            log.error("Dialog couldn't be loaded: " + ex);
        }
    }
}
