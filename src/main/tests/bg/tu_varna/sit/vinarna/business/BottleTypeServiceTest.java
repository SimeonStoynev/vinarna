package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.presentation.models.BottleTypeModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BottleTypeServiceTest {

    private BottleTypeService bottleTypeService;

    @BeforeEach
    void setUp() {
        bottleTypeService = BottleTypeService.getInstance();
    }

    @Test
    void getInstance() {
        assertTrue(bottleTypeService instanceof BottleTypeService);
        assertNotNull(bottleTypeService);
    }

    @Test
    void getAll() {
        assertTrue(bottleTypeService.getAll() instanceof ObservableList<BottleTypeModel>);
        assertNotNull(bottleTypeService.getAll());
    }

    @Test
    void isBottleCapacityExists() {
        assertTrue(bottleTypeService.isBottleCapacityExists(50.0));
    }
}