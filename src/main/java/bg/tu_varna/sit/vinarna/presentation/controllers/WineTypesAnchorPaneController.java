package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.WineTypeService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.presentation.models.WineTypeModel;
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
import org.apache.commons.math3.util.Precision;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.Objects;

public class WineTypesAnchorPaneController {
    private static final Logger log = Logger.getLogger(UsersAnchorPaneController.class);

    WineTypeService wineTypeService = WineTypeService.getInstance();

    @FXML
    AnchorPane wineTypesRowsAnchorPane;

    ObservableList<WineTypeModel> wineTypes;

    @FXML
    public void initialize() {
        wineTypesTableViewReload();
    }

    public void getWineTypes() {
        wineTypes = wineTypeService.getAllWineTypes();
    }

    public void wineTypesTableViewReload() {
        try {
            getWineTypes();
            wineTypesRowsAnchorPane.getChildren().clear();
            int y = 0;
            boolean bg = false;
            for(WineTypeModel wineType : wineTypes) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.View.WINETYPESTABLEROW_VIEW));
                AnchorPane userRow = loader.load();
                WineTypesTableRowController controller = loader.getController();

                controller.idLabel.setText(String.valueOf(wineType.getId()));
                controller.wineTypeNameLabel.setText(wineType.getName());
                controller.producedLabel.setText(String.valueOf(Precision.round(wineType.getProduced(), 3)) + " L");
                if(wineType.getProduced() == 0)
                    controller.producedLabel.setDisable(true);

                controller.editCustomMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        wineTypesAddEditDialogShow(wineType);
                    }
                });

                controller.produceCustomMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        wineTypesProduceDialogShow(wineType);
                    }
                });

                AnchorPane.setRightAnchor(userRow, 0.0);
                AnchorPane.setLeftAnchor(userRow, 0.0);
                userRow.setLayoutY(y);
                if(bg)
                    userRow.setStyle("-fx-background-color: rgba(66,65,54,0.21);");

                wineTypesRowsAnchorPane.getChildren().add(userRow);
                y+=45;
                bg=!bg;
            }
        } catch (Exception ex) {
            log.error("Wine types table reload error: " + ex.getMessage());
        }
    }

    public void wineTypeAddButton() {
        wineTypesAddEditDialogShow();
    }

    private void wineTypesAddEditDialogShow(WineTypeModel... wineType) {
        Stage stage = (Stage) wineTypesRowsAnchorPane.getScene().getWindow();

        try {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);

            URL path = WineTypesAddEditDialogController.class.getResource(Constants.View.WINETYPESADDEDITDIALOG_VIEW);
            if (path != null) {
                FXMLLoader loader = new FXMLLoader(WineTypesAddEditDialogController.class.getResource(Constants.View.WINETYPESADDEDITDIALOG_VIEW));
                Parent root = loader.load();


                Scene scene = new Scene(root);
                dialog.getIcons().add(new Image(Objects.requireNonNull(WineTypesAddEditDialogController.class.getResourceAsStream(Constants.Media.APP_ICON))));
                dialog.setScene(scene);
                dialog.setResizable(false);
                dialog.setTitle("Add wine type");

                WineTypesAddEditDialogController controller = loader.getController();
                controller.formInit(wineType);
                dialog.show();

                dialog.setOnHiding(new EventHandler<WindowEvent>(){
                    public void handle(WindowEvent we) {
                        dialog.close();
                        wineTypesTableViewReload();
                    }

                });

            } else {
                log.error("Dialog couldn't be loaded: " + Constants.View.USERSADDEDITDIALOG_VIEW);
            }
        } catch (Exception ex) {
            log.error("Dialog couldn't be loaded: " + ex);
        }
    }

    private void wineTypesProduceDialogShow(WineTypeModel wineType) {
        Stage stage = (Stage) wineTypesRowsAnchorPane.getScene().getWindow();

        try {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);

            URL path = WineTypesProduceDialogController.class.getResource(Constants.View.WINETYPESPRODUCEDIALOG_VIEW);
            if (path != null) {
                FXMLLoader loader = new FXMLLoader(WineTypesProduceDialogController.class.getResource(Constants.View.WINETYPESPRODUCEDIALOG_VIEW));
                Parent root = loader.load();


                Scene scene = new Scene(root);
                dialog.getIcons().add(new Image(Objects.requireNonNull(WineTypesProduceDialogController.class.getResourceAsStream(Constants.Media.APP_ICON))));
                dialog.setScene(scene);
                dialog.setResizable(false);
                dialog.setTitle("Wine produce");

                WineTypesProduceDialogController controller = loader.getController();
                controller.formInit(wineType);
                dialog.show();

                dialog.setOnHiding(new EventHandler<WindowEvent>(){
                    public void handle(WindowEvent we) {
                        dialog.close();
                        wineTypesTableViewReload();
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
