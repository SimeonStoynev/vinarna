package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.common.Hasher;
import bg.tu_varna.sit.vinarna.data.entities.User;
import bg.tu_varna.sit.vinarna.data.repositories.UserRepository;
import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
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

    public ObservableList<UserModel> getAllUser() {
        List<User> users = repository.getAll();
        return FXCollections.observableList(
                users.stream().map(u -> new UserModel(
                        u.getId(),
                        u.getRole(),
                        u.getUsername(),
                        u.getPassword(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getEmail(),
                        u.getPhone(),
                        u.getCreated_at(),
                        u.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }

    public boolean userAuth(String username, String password) {
        User userTmp = repository.getByUsernameAndPassword(username, password);
        return userTmp != null;
    }

}


