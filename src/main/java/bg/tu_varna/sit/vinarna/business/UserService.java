package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.User;
import bg.tu_varna.sit.vinarna.data.repositories.UserRepository;
import bg.tu_varna.sit.vinarna.presentation.models.RoleModel;
import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
                        new RoleModel(u.getRole()),
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

    public UserModel getUserByUsername(String username) {
        User user = repository.getByUsername(username);
        return (user == null) ? null : new UserModel(user);
    }

    public boolean isUsernameExists(String username) {
        return getUserByUsername(username) != null;
    }

    public UserModel getUserByEmail(String email) {
        User user = repository.getByEmail(email);
        return (user == null) ? null : new UserModel(user);
    }

    public boolean isEmailExists(String email) {
        return getUserByEmail(email) != null;
    }

    public int userAdd(UserModel user) {
        repository.save(user.toEntity());
        return 0;
    }

    public int userUpdate(UserModel user) {
        repository.update(user.toEntity());
        return 0;
    }

    public boolean userAuth(String username, String password) {
        User userTmp = repository.getByUsernameAndPassword(username, password);
        return userTmp != null;
    }

    public boolean usernameValidate(String username) {
        String regex = "^[A-Za-z]\\w{3,29}$";
        Pattern p = Pattern.compile(regex);
        if(username == null)
            return false;
        Matcher m = p.matcher(username);
        return m.matches();
    }

    public boolean passwordValidate(String password) {
        String regex = "^[A-Za-z]\\w{4,29}$";
        Pattern p = Pattern.compile(regex);
        if(password == null)
            return false;
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public boolean emailValidate(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
                + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern p = Pattern.compile(regex);
        if(email == null)
            return false;
        Matcher m = p.matcher(email);
        return m.matches();
    }



}


