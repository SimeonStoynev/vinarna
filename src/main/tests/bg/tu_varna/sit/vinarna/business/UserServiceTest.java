package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = UserService.getInstance();
    }

    @Test
    void getInstance() {
        assertTrue(userService instanceof UserService);
        assertNotNull(userService);
    }

    @Test
    void getAllUser() {
        assertTrue(userService.getAllUser() instanceof ObservableList<UserModel>);
        assertNotNull(userService.getAllUser());
    }

    @Test
    void getUserByEmail() {
        assertNull(userService.getUserByEmail("nonexisting_email@gmail.com"));
    }

    @Test
    void isEmailExists() {
        assertFalse(userService.isEmailExists("nonexisting_email@gmail.com"));
    }

    @Test
    void userAuth() {
        // Non-existent user
        assertFalse(userService.userAuth("Empty", "12345"));

        // Real user
        assertTrue(userService.userAuth("simo", "test"));
    }
}