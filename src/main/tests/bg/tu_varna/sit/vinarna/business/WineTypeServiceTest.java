package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.presentation.models.WineTypeModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WineTypeServiceTest {

    private WineTypeService wineTypeService;

    @BeforeEach
    void setUp() {
        wineTypeService = WineTypeService.getInstance();
    }

    @Test
    void getInstance() {
        assertTrue(wineTypeService instanceof WineTypeService);
        assertNotNull(wineTypeService);
    }

    @Test
    void getAllWineTypes() {
        assertTrue(wineTypeService.getAllWineTypes() instanceof ObservableList<WineTypeModel>);
        assertNotNull(wineTypeService.getAllWineTypes());
    }
}