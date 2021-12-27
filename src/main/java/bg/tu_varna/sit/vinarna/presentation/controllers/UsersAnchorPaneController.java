package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.data.entities.User;
import bg.tu_varna.sit.vinarna.data.repositories.UserRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class UsersAnchorPaneController {
    private static final Logger log = Logger.getLogger(UsersAnchorPaneController.class);

    public final UserRepository repository = UserRepository.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    AnchorPane userRowsAnchorPane;

    @FXML
    private void initialize() {
        userTableViewReload();
    }

    public void userTableViewReload() {
        try {
            List<User> users = repository.getAll();
            userRowsAnchorPane.getChildren().clear();
            int y = 0;
            boolean bg = false;
            for(User user : users) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/bg/tu_varna/sit/vinarna/presentation/views/Users/UsersTableRow.fxml"));
                AnchorPane userRow = loader.load();
                UsersTableRowController controller = loader.getController();

                controller.idLabel.setText(String.valueOf(user.getId()));
                controller.usernameLabel.setText(user.getUsername());
                controller.firstNameLabel.setText(user.getFirstName());
                controller.lastNameLabel.setText(user.getLastName());
                controller.emailLabel.setText(user.getEmail());
                controller.phoneLabel.setText(user.getPhone());
                controller.roleLabel.setText(user.getRole().getName());
                if(user.getRole().getId() == 1)
                    controller.settingsMenuButton.setVisible(false);

                AnchorPane.setRightAnchor(userRow, 0.0);
                AnchorPane.setLeftAnchor(userRow, 0.0);
                userRow.setLayoutY(y);
                if(bg)
                    userRow.setStyle("-fx-background-color: rgba(66,65,54,0.21);");

                userRowsAnchorPane.getChildren().add(userRow);
                y+=45;
                bg=!bg;
            }
        } catch (Exception ex) {
            log.error("User table reload error: " + ex.getMessage());
        }

    }
}
