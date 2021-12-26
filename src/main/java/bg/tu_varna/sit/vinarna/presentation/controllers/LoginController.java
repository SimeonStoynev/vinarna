package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.common.Hasher;
import bg.tu_varna.sit.vinarna.data.entities.User;
import bg.tu_varna.sit.vinarna.data.repositories.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController {
    private static final Logger log = Logger.getLogger(LoginController.class);
    private final UserRepository userRepository = UserRepository.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    Button loginButton;

    @FXML
    TextField usernameTextField;

    @FXML
    TextField passwordTextField;

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

    public void loginProcedure() {
        messageLabel.setText("");

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if(username.length() == 0 || password.length() == 0) {
            messageLabel.setStyle("-fx-text-fill: #f54c38;");
            messageLabel.setText("The fields must be filled.");
            return;
        }else if(!usernameValidate(username) || !passwordValidate(password)) {
            messageLabel.setStyle("-fx-text-fill: #f54c38;");
            messageLabel.setText("There is no user with the entered data.");
            return;
        }

        password = Hasher.MD5.hash(password);
        User user = userRepository.getByUsernameAndPassword(username, password);
        if(user == null) {
            messageLabel.setStyle("-fx-text-fill: #f54c38;");
            messageLabel.setText("There is no user with the entered data.");
            return;
        } else {
            System.out.println("Successful login.");
        }
    }

    private boolean usernameValidate(String username) {
        String regex = "^[A-Za-z]\\w{3,29}$";
        Pattern p = Pattern.compile(regex);

        if(username == null)
            return false;

        Matcher m = p.matcher(username);

        return m.matches();
    }

    private boolean passwordValidate(String password) {
        String regex = "^[A-Za-z]\\w{4,29}$";
        Pattern p = Pattern.compile(regex);

        if(password == null)
            return false;

        Matcher m = p.matcher(password);

        return m.matches();
    }




}
