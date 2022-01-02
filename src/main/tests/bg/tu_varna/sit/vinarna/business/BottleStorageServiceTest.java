package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.presentation.models.BottleStorageModel;
import bg.tu_varna.sit.vinarna.presentation.models.BottleTypeModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BottleStorageServiceTest {

    private BottleStorageService bottleStorageService;

    @BeforeEach
    void setUp() {
        bottleStorageService = BottleStorageService.getInstance();
    }

    @Test
    void getInstance() {
        assertTrue(bottleStorageService instanceof BottleStorageService);
        assertNotNull(bottleStorageService);
    }

    @Test
    void getAll() {
        assertTrue(bottleStorageService.getAll() instanceof ObservableList<BottleStorageModel>);
        assertNotNull(bottleStorageService.getAll());
    }

    @Test
    void getLatestAll() {
        assertTrue(bottleStorageService.getLatestAll() instanceof ObservableList<BottleStorageModel>);
        assertNotNull(bottleStorageService.getLatestAll());
    }

    @Test
    void getAllByBottle() {
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        BottleTypeModel bottleTypeModel = new BottleTypeModel(1, 375.0, currentTimestamp, currentTimestamp);

        assertTrue(bottleStorageService.getAllByBottle(bottleTypeModel) instanceof ObservableList<BottleStorageModel>);
        assertNotNull(bottleStorageService.getAllByBottle(bottleTypeModel));
    }
}