package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.GrapeSortService;
import bg.tu_varna.sit.vinarna.business.GrapeStorageService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeStorageModel;
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

public class GrapeSortsAnchorPaneController {
    private static final Logger log = Logger.getLogger(GrapeSortsAnchorPaneController.class);

    public final GrapeSortService grapeSortService = GrapeSortService.getInstance();
    public final GrapeStorageService grapeStorageService = GrapeStorageService.getInstance();

    @FXML
    AnchorPane sortRowsAnchorPane;

    ObservableList<GrapeSortModel> grapeSorts;
    ObservableList<GrapeStorageModel> grapeStorage;

    @FXML
    private void initialize() {
        grapeSortsTableViewReload();
    }

    public void getGrapeSorts() {
        grapeSorts = grapeSortService.getAllSorts();
        grapeStorage = grapeStorageService.getLatestAll();
    }

    public void grapeSortsTableViewReload() {
        try {
            getGrapeSorts();
            sortRowsAnchorPane.getChildren().clear();

            int y = 0;
            boolean bg = false;

            for(GrapeSortModel grapeSort : grapeSorts) {
                GrapeStorageModel quantity = grapeStorage.stream()
                        .filter(storage -> grapeSort.getId() == storage.getSort().getId())
                        .findFirst()
                        .orElse(null);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/bg/tu_varna/sit/vinarna/presentation/views/Grapes/GrapeSortsTableRow.fxml"));
                AnchorPane sortRow = loader.load();
                GrapeSortsTableRowController controller = loader.getController();

                controller.idLabel.setText(String.valueOf(grapeSort.getId()));
                controller.sortNameLabel.setText(grapeSort.getName());
                controller.sortCategoryLabel.setText(grapeSort.getCategory().getCategory());
                controller.litersPerKGLabel.setText(String.valueOf(grapeSort.getWine_liters()) + " L");
                controller.addQuantityCustomMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        grapeQuantityAddDialogShow(grapeSort);
                    }
                });

                if(quantity != null) {
                    controller.sortQuantityLabel.setText(String.valueOf(quantity.getQuantity())+" kg");
                    if(quantity.getQuantity() < Constants.Minima.GRAPE_MINIMUM) {
                        controller.sortQuantityLabel.getStyleClass().add("text-error");
                    }
                } else {
                    controller.sortQuantityLabel.setText("0 kg");
                    controller.sortQuantityLabel.setDisable(true);
                }

                AnchorPane.setRightAnchor(sortRow, 0.0);
                AnchorPane.setLeftAnchor(sortRow, 0.0);
                sortRow.setLayoutY(y);
                if(bg)
                    sortRow.setStyle("-fx-background-color: rgba(66,65,54,0.21);");

                sortRowsAnchorPane.getChildren().add(sortRow);
                y+=45;
                bg=!bg;
            }
        } catch (Exception ex) {
            log.error("GrapeSorts table reload error: " + ex.getMessage());
        }
    }

    public void grapeSortAddButton() {
        grapeSortAddDialogShow();
    }

    public void grapeQuantityAddButton() {
        grapeQuantityAddDialogShow();
    }

    private void grapeSortAddDialogShow() {
        Stage stage = (Stage) sortRowsAnchorPane.getScene().getWindow();

        try {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);

            URL path = GrapeSortsAddEditDialogController.class.getResource(Constants.View.GRAOESORTSADDEDITDIALOG_VIEW);
            if (path != null) {
                FXMLLoader loader = new FXMLLoader(GrapeSortsAddEditDialogController.class.getResource(Constants.View.GRAOESORTSADDEDITDIALOG_VIEW));
                Parent root = loader.load();


                Scene scene = new Scene(root);
                dialog.getIcons().add(new Image(Objects.requireNonNull(GrapeSortsAddEditDialogController.class.getResourceAsStream(Constants.Media.APP_ICON))));
                dialog.setScene(scene);
                dialog.setResizable(false);
                dialog.setTitle("Add Grape Sort");

                GrapeSortsAddEditDialogController controller = loader.getController();
                //controller.formInit(grapeSort);
                dialog.show();

                dialog.setOnHiding(new EventHandler<WindowEvent>(){
                    public void handle(WindowEvent we) {
                        dialog.close();
                        grapeSortsTableViewReload();
                    }

                });

            } else {
                log.error("Dialog couldn't be loaded: " + Constants.View.USERSADDEDITDIALOG_VIEW);
            }
        } catch (Exception ex) {
            log.error("Dialog couldn't be loaded: " + ex);
        }
    }

    public void grapeQuantityAddDialogShow(GrapeSortModel... grapeSort) {
        Stage stage = (Stage) sortRowsAnchorPane.getScene().getWindow();

        try {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);

            URL path = GrapeQuantityAddDialogController.class.getResource(Constants.View.GRAPEQUANTITYADDDIALOG_VIEW);
            if (path != null) {
                FXMLLoader loader = new FXMLLoader(GrapeQuantityAddDialogController.class.getResource(Constants.View.GRAPEQUANTITYADDDIALOG_VIEW));
                Parent root = loader.load();


                Scene scene = new Scene(root);
                dialog.getIcons().add(new Image(Objects.requireNonNull(GrapeQuantityAddDialogController.class.getResourceAsStream(Constants.Media.APP_ICON))));
                dialog.setScene(scene);
                dialog.setResizable(false);
                dialog.setTitle("Add Grape Quantity");

                GrapeQuantityAddDialogController controller = loader.getController();
                controller.formInit(grapeSort);
                dialog.show();

                dialog.setOnHiding(new EventHandler<WindowEvent>(){
                    public void handle(WindowEvent we) {
                        dialog.close();
                        grapeSortsTableViewReload();
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
