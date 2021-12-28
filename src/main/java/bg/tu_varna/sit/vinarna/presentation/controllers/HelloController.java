package bg.tu_varna.sit.vinarna.presentation.controllers;

import bg.tu_varna.sit.vinarna.business.UserService;
import bg.tu_varna.sit.vinarna.presentation.models.HelloModel;
import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.*;

public class HelloController implements EventHandler<MouseEvent> {

    private final UserService service = UserService.getInstance();
    @FXML
    private Label welcomeText;

    @FXML
    private Button helloButton;

    @FXML
    private ListView<UserModel> listView;

    private final HelloModel model;

    public HelloController() {
        this.model = new HelloModel();
    }
    
    @FXML
    private void initialize() {
        helloButton.setOnMouseClicked(this::handle);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        /*welcomeText.setText(model.getWelcomeMessage());

        ObservableList<UserListViewModel> userListViewModels = service.getAllUser();
        listView.setItems(userListViewModels);*/
    }
}