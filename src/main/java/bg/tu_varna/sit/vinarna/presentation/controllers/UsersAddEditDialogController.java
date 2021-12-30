package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.RoleService;
import bg.tu_varna.sit.vinarna.business.UserService;
import bg.tu_varna.sit.vinarna.common.Hasher;
import bg.tu_varna.sit.vinarna.presentation.models.RoleModel;
import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.util.Date;

public class UsersAddEditDialogController {

    public final UserService userService = UserService.getInstance();
    public final RoleService roleService = RoleService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

    @FXML
    AnchorPane formAnchorPane;

    @FXML
    Label titleLabel;

    @FXML
    TextField usernameTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    TextField passwordRepeatTextField;

    @FXML
    Label passwordInfoLabel;

    @FXML
    ComboBox rolesComboBox;

    @FXML
    TextField firstNameTextField;

    @FXML
    TextField lastNameTextField;

    @FXML
    TextField emailTextField;

    @FXML
    TextField phoneTextField;

    @FXML
    Button submitButton;

    @FXML
    Label usernameErrorLabel;

    @FXML
    Label passwordErrorLabel;

    @FXML
    Label roleErrorLabel;

    @FXML
    Label fnameErrorLabel;

    @FXML
    Label lnameErrorLabel;

    @FXML
    Label emailErrorLabel;

    @FXML
    Label phoneErrorLabel;

    UserModel user;
    ObservableList<RoleModel> roles;
    int action = 0;

    @FXML
    private void initialize() {
        errorLabelsClear();
        mainAnchorPanel.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                submitButton.fire();
                keyEvent.consume();
            }
        });
    }

    public void formInit(UserModel... user) {
        roles = roleService.getAllRoles();

        roles.remove(0); // Removing the Admin role from the list, as in the task is specified that only
                               // operators and warehouse hosts can be created.
        rolesComboBox.getItems().addAll(roles);

        if(user.length == 0) {

            titleLabel.setText("Create User");
            submitButton.setText("Create User");
            passwordInfoLabel.setVisible(false);

        } else {
            action = 1;
            this.user = user[0];
            titleLabel.setText("Edit User");
            submitButton.setText("Edit User");

            usernameTextField.setText(user[0].getUsername());
            firstNameTextField.setText(user[0].getFirstName());
            lastNameTextField.setText(user[0].getLastName());
            emailTextField.setText(user[0].getEmail());
            phoneTextField.setText(user[0].getPhone());
            rolesComboBox.setValue(user[0].getRole());
        }
    }

    public void submitForm() {
        errorLabelsClear();

        boolean valid = (action == 0) ? formAddValidate() : formEditValidate();
        if(valid) {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            RoleModel role = (RoleModel)rolesComboBox.getValue();
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String email = emailTextField.getText();
            String phone = phoneTextField.getText();

            if(user == null) {
                user = new UserModel();
                user.setId(0);
            }

            user.setRole(role);
            user.setUsername(username);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            Date date = new Date();
            user.setCreated_at(new Timestamp(date.getTime()));
            user.setUpdated_at(new Timestamp(date.getTime()));

            if(action == 0) {
                user.setPassword(Hasher.MD5.hash(password));
                userService.userAdd(user);
            }
            else {
                if(password.length() != 0)
                    user.setPassword(Hasher.MD5.hash(password));
                userService.userUpdate(user);
            }
            Stage stage = (Stage) mainAnchorPanel.getScene().getWindow();
            stage.hide();
        }
    }

    private boolean formAddValidate() {
        boolean valid = true;
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String passwordRepeat = passwordRepeatTextField.getText();
        RoleModel role = (RoleModel)rolesComboBox.getValue();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();

        if(!userService.usernameValidate(username)) {
            usernameErrorLabel.setText("Username is not valid.");
            valid = false;
        } else if (userService.isUsernameExists(username)) {
            usernameErrorLabel.setText("Username is already in use.");
            valid = false;
        }

        if(!userService.passwordValidate(password)){
            passwordErrorLabel.setText("Password is not valid.");
            valid = false;
        } else if(!passwordRepeat.equals(password)) {
            passwordErrorLabel.setText("The repeat password do not match.");
            valid = false;
        }

        if(!userService.emailValidate(email)) {
            emailErrorLabel.setText("Email is not valid.");
            valid = false;
        } else if(userService.isEmailExists(email)) {
            emailErrorLabel.setText("Email is already in use.");
            valid = false;
        }

        if(role == null) {
            roleErrorLabel.setText("Role is not selected.");
            valid = false;
        }

        if(firstName.length() == 0) {
            fnameErrorLabel.setText("The first name is required.");
            valid = false;
        }

        if(lastName.length() == 0) {
            lnameErrorLabel.setText("The last name is required.");
            valid = false;
        }

        if(phone.length() == 0) {
            phoneErrorLabel.setText("The phone is required.");
            valid = false;
        }

        return valid;
    }

    private boolean formEditValidate() {
        boolean valid = true;
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String passwordRepeat = passwordRepeatTextField.getText();
        RoleModel role = (RoleModel)rolesComboBox.getValue();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();

        if(!userService.usernameValidate(username)) {
            usernameErrorLabel.setText("Username is not valid.");
            valid = false;
        } else if (!user.getUsername().equals(username) && userService.isUsernameExists(username)) {
            usernameErrorLabel.setText("Username is already in use.");
            valid = false;
        }

        if(password.length() != 0 && !userService.passwordValidate(password)){
            passwordErrorLabel.setText("Password is not valid.");
            valid = false;
        } else if(!passwordRepeat.equals(password)) {
            passwordErrorLabel.setText("The repeat password do not match.");
            valid = false;
        }

        if(!userService.emailValidate(email)) {
            emailErrorLabel.setText("Email is not valid.");
            valid = false;
        } else if(!user.getEmail().equals(email) && userService.isEmailExists(email)) {
            emailErrorLabel.setText("Email is already in use.");
            valid = false;
        }

        if(role == null) {
            roleErrorLabel.setText("Role is not selected.");
            valid = false;
        }

        if(firstName.length() == 0) {
            fnameErrorLabel.setText("The first name is required.");
            valid = false;
        }

        if(lastName.length() == 0) {
            lnameErrorLabel.setText("The last name is required.");
            valid = false;
        }

        if(phone.length() == 0) {
            phoneErrorLabel.setText("The phone is required.");
            valid = false;
        }

        return valid;
    }

    private void errorLabelsClear() {
        usernameErrorLabel.setText("");
        passwordErrorLabel.setText("");
        roleErrorLabel.setText("");
        fnameErrorLabel.setText("");
        lnameErrorLabel.setText("");
        emailErrorLabel.setText("");
        phoneErrorLabel.setText("");
    }
}
