package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.presentation.models.GrapeStorageModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrapeStorageServiceTest {

    private GrapeStorageService grapeStorageService;

    @BeforeEach
    void setUp() {
        grapeStorageService = GrapeStorageService.getInstance();
    }

    @Test
    void getInstance() {
        assertTrue(grapeStorageService instanceof GrapeStorageService);
        assertNotNull(grapeStorageService);
    }

    @Test
    void getAll() {
        assertTrue(grapeStorageService.getAll() instanceof ObservableList<GrapeStorageModel>);
        assertNotNull(grapeStorageService.getAll());
    }

    @Test
    void getLatestAll() {
        assertTrue(grapeStorageService.getLatestAll() instanceof ObservableList<GrapeStorageModel>);
        assertNotNull(grapeStorageService.getLatestAll());
    }
}