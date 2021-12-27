package bg.tu_varna.sit.vinarna.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.apache.log4j.Logger;

import java.io.IOException;

public class UsersAnchorPaneController {
    private static final Logger log = Logger.getLogger(UsersAnchorPaneController.class);

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    AnchorPane userRowsAnchorPane;

    @FXML
    private void initialize() throws IOException {


        userRowsAnchorPane.getChildren().remove(0);
        int y = 0;
        boolean bg = false;
        for(int i = 0; i < 100; i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bg/tu_varna/sit/vinarna/presentation/views/Users/UsersTableRow.fxml"));
            AnchorPane user = loader.load();
            UsersTableRowController controller = loader.getController();
            controller.idLabel.setText(String.valueOf(i));

            AnchorPane.setRightAnchor(user, 0.0);
            AnchorPane.setLeftAnchor(user, 0.0);
            user.setLayoutY(y);
            if(bg)
                user.setStyle("-fx-background-color: rgba(66,65,54,0.21);");

            userRowsAnchorPane.getChildren().add(user);
            y+=45;
            bg=!bg;
        }

    }
}
