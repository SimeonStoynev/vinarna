package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.UserService;
import bg.tu_varna.sit.vinarna.common.Constants;
import bg.tu_varna.sit.vinarna.common.Hasher;
import bg.tu_varna.sit.vinarna.common.ViewsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;

public class LoginController {
    private static final Logger log = Logger.getLogger(LoginController.class);
    public final UserService userService = UserService.getInstance();


    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    Button loginButton;

    @FXML
    TextField usernameTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    Label messageLabel;

    @FXML
    private void initialize() {
        messageLabel.setText("");
        mainAnchorPanel.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                loginButton.fire();
                keyEvent.consume();
            }
        });
    }

    public void loginProcedure() throws IOException {
        messageLabel.setText("");

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if(username.length() == 0 || password.length() == 0) {
            messageLabel.setStyle("-fx-text-fill: " + Constants.Values.ERROR_COLOR + ";");
            messageLabel.setText("The fields must be filled.");
            return;
        }else if(!userService.usernameValidate(username) || !userService.passwordValidate(password)) {
            messageLabel.setStyle("-fx-text-fill: " + Constants.Values.ERROR_COLOR + ";");
            messageLabel.setText("There is no user with the entered data.");
            return;
        }

        password = Hasher.MD5.hash(password);
        boolean loginAuth = userService.userAuth(username, password);

        if(!loginAuth) {
            messageLabel.setStyle("-fx-text-fill: " + Constants.Values.ERROR_COLOR + ";");
            messageLabel.setText("There is no user with the entered data.");
            return;
        } else {
            Stage stage = (Stage) mainAnchorPanel.getScene().getWindow();
            ViewsManager.changeView("Dashboard", Constants.View.DASHBOARD_VIEW, DashboardController.class, stage);
            System.out.println("Successful login.");
        }
    }
}
