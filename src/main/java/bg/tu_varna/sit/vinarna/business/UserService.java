package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.User;
import bg.tu_varna.sit.vinarna.data.repositories.UserRepository;
import bg.tu_varna.sit.vinarna.presentation.models.UserListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository repository = UserRepository.getInstance();

    public static UserService getInstance() {
        return UserServiceHolder.INSTANCE;
    }

    private static class UserServiceHolder {
        public static final UserService INSTANCE = new UserService();
    }

    public ObservableList<UserListViewModel> getAllUser() {
        List<User> users = repository.getAll();
        return FXCollections.observableList(
                users.stream().map(u -> new UserListViewModel(
                    u.getUsername(),
                    u.getFirstName(),
                    u.getLastName()
                )).collect(Collectors.toList())
        );
    }
}


