package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.presentation.models.GrapeCategoryModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrapeCategoryServiceTest {

    private GrapeCategoryService grapeCategoryService;

    @BeforeEach
    void setUp() {
        grapeCategoryService = GrapeCategoryService.getInstance();
    }

    @Test
    void getInstance() {
        assertTrue(grapeCategoryService instanceof GrapeCategoryService);
        assertNotNull(grapeCategoryService);
    }

    @Test
    void getAll() {
        assertTrue(grapeCategoryService.getAll() instanceof ObservableList<GrapeCategoryModel>);
        assertNotNull(grapeCategoryService.getAll());
    }
}