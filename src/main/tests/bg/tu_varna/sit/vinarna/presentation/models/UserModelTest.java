package bg.tu_varna.sit.vinarna.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserModelTest {

    private RoleModel roleModel;
    private UserModel userModel;
    private final Timestamp currentTimestamp = new Timestamp(new Date().getTime());

    @BeforeEach
    void setUp() {
        roleModel = new RoleModel(1, "System Admin", currentTimestamp, currentTimestamp);
        userModel = new UserModel(1, roleModel, "Test", "123456", "System", "Admin", "test@gmail.com", "0123456789", currentTimestamp, currentTimestamp);
    }

    @Test
    void testUserModelData() {
        assertEquals(1, userModel.getId());
        assertEquals(roleModel, userModel.getRole());
        assertEquals("Test", userModel.getUsername());
        assertEquals("123456", userModel.getPassword());
        assertEquals("System", userModel.getFirstName());
        assertEquals("Admin", userModel.getLastName());
        assertEquals("test@gmail.com", userModel.getEmail());
        assertEquals("0123456789", userModel.getPhone());
        assertEquals(currentTimestamp, userModel.getCreated_at());
        assertEquals(currentTimestamp, userModel.getUpdated_at());
    }
}