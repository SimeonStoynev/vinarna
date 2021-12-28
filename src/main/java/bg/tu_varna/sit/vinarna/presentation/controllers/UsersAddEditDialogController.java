package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.RoleService;
import bg.tu_varna.sit.vinarna.business.UserService;
import bg.tu_varna.sit.vinarna.data.entities.Role;
import bg.tu_varna.sit.vinarna.data.entities.User;
import bg.tu_varna.sit.vinarna.data.repositories.RoleRepository;
import bg.tu_varna.sit.vinarna.data.repositories.UserRepository;
import bg.tu_varna.sit.vinarna.presentation.models.RoleModel;
import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class UsersAddEditDialogController {

    public final UserService userService = UserService.getInstance();
    public final RoleService roleService = RoleService.getInstance();

    @FXML
    AnchorPane mainAnchorPanel;

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

    UserModel user;
    ObservableList<RoleModel> roles;

    @FXML
    private void initialize() {

    }

    public void formInit(UserModel... user) {
        roles = roleService.getAllRoles();

        for(RoleModel role : roles) {
            if(role.getId() != 1)
                rolesComboBox.getItems().add(role.getName());
        }

        if(user.length == 0) {

            titleLabel.setText("Create User");
            submitButton.setText("Create User");
            passwordInfoLabel.setVisible(false);

        } else {
            this.user = user[0];
            titleLabel.setText("Edit User");
            submitButton.setText("Edit User");

            usernameTextField.setText(user[0].getUsername());
            firstNameTextField.setText(user[0].getFirstName());
            lastNameTextField.setText(user[0].getLastName());
            emailTextField.setText(user[0].getEmail());
            phoneTextField.setText(user[0].getPhone());
            rolesComboBox.setValue(user[0].getRole().getName());
        }
    }

    public void submitForm() {
        if(this.user == null){
            System.out.println("Nqma");
        } else {
            System.out.println("Ima");
        }
    }
}
