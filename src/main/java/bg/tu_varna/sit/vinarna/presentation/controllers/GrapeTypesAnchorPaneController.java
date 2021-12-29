package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.GrapeSortService;
import bg.tu_varna.sit.vinarna.business.GrapeStorageService;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeStorageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;

public class GrapeTypesAnchorPaneController {
    private static final Logger log = Logger.getLogger(GrapeTypesAnchorPaneController.class);

    public final GrapeSortService grapeSortService = GrapeSortService.getInstance();
    public final GrapeStorageService grapeStorageService = GrapeStorageService.getInstance();


    @FXML
    AnchorPane sortRowsAnchorPane;

    ObservableList<GrapeSortModel> grapeSorts;
    ObservableList<GrapeStorageModel> grapeStorage;

    @FXML
    private void initialize() {
        grapeSortsTableViewReload();
        //System.out.println(grapeStorage);
    }

    public void getGrapeSorts() {
        grapeSorts = grapeSortService.getAllSorts();
        grapeStorage = grapeStorageService.getAll();
        FXCollections.reverse(grapeStorage);
    }

    public void grapeSortsTableViewReload() {
        try {
            getGrapeSorts();
            sortRowsAnchorPane.getChildren().clear();


            int y = 0;
            boolean bg = false;

            for(GrapeSortModel grapeSort : grapeSorts) {

                GrapeStorageModel james = grapeStorage.stream()
                        .filter(customer -> grapeSort.getId() == customer.getSort().getId())
                        .findFirst()
                        .orElse(null);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/bg/tu_varna/sit/vinarna/presentation/views/Grapes/GrapeTypesTableRow.fxml"));
                AnchorPane sortRow = loader.load();
                GrapeTypesTableRowController controller = loader.getController();

                controller.idLabel.setText(String.valueOf(grapeSort.getId()));
                controller.sortNameLabel.setText(grapeSort.getName());
                controller.sortCategoryLabel.setText(grapeSort.getCategory().getCategory());

                if(james != null) {
                    controller.sortQuantityLabel.setText(String.valueOf(james.getQuantity())+" kg");
                } else {
                    controller.sortQuantityLabel.setText("0 kg");
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
}
