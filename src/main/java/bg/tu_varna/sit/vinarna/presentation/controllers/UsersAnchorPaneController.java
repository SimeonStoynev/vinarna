package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.UserService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
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
import java.util.Objects;

public class UsersAnchorPaneController {
    private static final Logger log = Logger.getLogger(UsersAnchorPaneController.class);

    public final UserService userService = UserService.getInstance();

    @FXML
    AnchorPane userRowsAnchorPane;

    ObservableList<UserModel> users;

    @FXML
    private void initialize() {
        userTableViewReload();
    }

    public void getUsers() {
        users = userService.getAllUser();
    }

    public void userTableViewReload() {
        try {
            getUsers();
            userRowsAnchorPane.getChildren().clear();
            int y = 0;
            boolean bg = false;
            for(UserModel user : users) {
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
                controller.editCustomMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        userAddEditDialogShow(user);
                    }
                });
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

    public void userAddDialog() {
        userAddEditDialogShow();
    }

    private void userAddEditDialogShow(UserModel... user) {
        Stage stage = (Stage) userRowsAnchorPane.getScene().getWindow();

        try {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);

            URL path = UsersAnchorPaneController.class.getResource(Constants.View.USERSADDEDITDIALOG_VIEW);
            if (path != null) {
                FXMLLoader loader = new FXMLLoader(UsersAnchorPaneController.class.getResource(Constants.View.USERSADDEDITDIALOG_VIEW));
                Parent root = loader.load();


                Scene scene = new Scene(root);
                dialog.getIcons().add(new Image(Objects.requireNonNull(UsersAnchorPaneController.class.getResourceAsStream(Constants.Media.APP_ICON))));
                dialog.setScene(scene);
                dialog.setResizable(false);
                dialog.setTitle("Add user");

                UsersAddEditDialogController controller = loader.getController();
                controller.formInit(user);
                dialog.show();

                dialog.setOnHiding(new EventHandler<WindowEvent>(){
                    public void handle(WindowEvent we) {
                        dialog.close();
                        userTableViewReload();
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
