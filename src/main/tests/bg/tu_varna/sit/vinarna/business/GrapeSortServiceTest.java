package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrapeSortServiceTest {

    private GrapeSortService grapeSortService;

    @BeforeEach
    void setUp() {
        grapeSortService = GrapeSortService.getInstance();
    }

    @Test
    void getInstance() {
        assertTrue(grapeSortService instanceof GrapeSortService);
        assertNotNull(grapeSortService);
    }

    @Test
    void getAllSorts() {
        assertTrue(grapeSortService.getAllSorts() instanceof ObservableList<GrapeSortModel>);
        assertNotNull(grapeSortService.getAllSorts());
    }

    @Test
    void isSortNameExists() {
        assertFalse(grapeSortService.isSortNameExists("Test Grape"));
    }
}