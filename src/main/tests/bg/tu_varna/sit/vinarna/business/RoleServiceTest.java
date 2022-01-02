package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.presentation.models.RoleModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceTest {

    private RoleService roleService;

    @BeforeEach
    void setUp() {
        roleService = RoleService.getInstance();
    }

    @Test
    void getInstance() {
        assertTrue(roleService instanceof RoleService);
        assertNotNull(roleService);
    }

    @Test
    void getAllRoles() {
        assertTrue(roleService.getAllRoles() instanceof ObservableList<RoleModel>);
        assertNotNull(roleService.getAllRoles());
    }
}