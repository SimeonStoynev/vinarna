package bg.tu_varna.sit.vinarna.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleModelTest {

    private RoleModel roleModel;
    private final Timestamp currentTimestamp = new Timestamp(new Date().getTime());

    @BeforeEach
    void setUp() {
        roleModel = new RoleModel(1, "System Admin", currentTimestamp, currentTimestamp);
    }

    @Test
    void testRoleModelData() {
        assertEquals(1, roleModel.getId());
        assertEquals("System Admin", roleModel.getName());
        assertEquals(currentTimestamp, roleModel.getCreated_at());
        assertEquals(currentTimestamp, roleModel.getUpdated_at());
    }

    @Test
    void testToString() {
        assertEquals(
            String.format("%s", roleModel.getName()),
            roleModel.toString()
        );
    }
}