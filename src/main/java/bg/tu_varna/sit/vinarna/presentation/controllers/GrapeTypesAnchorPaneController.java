package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.GrapeSortService;
import bg.tu_varna.sit.vinarna.data.entities.GrapeSort;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;

public class GrapeTypesAnchorPaneController {
    private static final Logger log = Logger.getLogger(GrapeTypesAnchorPaneController.class);

    public final GrapeSortService grapeSortService = GrapeSortService.getInstance();

    @FXML
    AnchorPane sortRowsAnchorPane;

    ObservableList<GrapeSortModel> grapeSorts;

    @FXML
    private void initialize() {
        grapeSortsTableViewReload();
        System.out.println(grapeSorts);
    }

    public void getGrapeSorts() {
        grapeSorts = grapeSortService.getAllSorts();
    }

    public void grapeSortsTableViewReload() {
        try {
            getGrapeSorts();
            sortRowsAnchorPane.getChildren().clear();

            int y = 0;
            boolean bg = false;

            for(GrapeSortModel grapeSort : grapeSorts) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/bg/tu_varna/sit/vinarna/presentation/views/Grapes/GrapeTypesTableRow.fxml"));
                AnchorPane sortRow = loader.load();
                GrapeTypesTableRowController controller = loader.getController();

                controller.idLabel.setText(String.valueOf(grapeSort.getId()));
                controller.sortNameLabel.setText(grapeSort.getName());
                controller.sortCategoryLabel.setText(grapeSort.getCategory().getCategory());

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

        }
    }
}
