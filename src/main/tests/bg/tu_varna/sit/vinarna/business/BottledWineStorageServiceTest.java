package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.presentation.models.BottledWineStorageModel;
import bg.tu_varna.sit.vinarna.presentation.models.WineTypeModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BottledWineStorageServiceTest {

    private BottledWineStorageService bottledWineStorageService;

    @BeforeEach
    void setUp() {
        bottledWineStorageService = BottledWineStorageService.getInstance();
    }

    @Test
    void getInstance() {
        assertTrue(bottledWineStorageService instanceof BottledWineStorageService);
        assertNotNull(bottledWineStorageService);
    }

    @Test
    void getAll() {
        assertTrue(bottledWineStorageService.getAll() instanceof ObservableList<BottledWineStorageModel>);
        assertNotNull(bottledWineStorageService.getAll());
    }

    @Test
    void getLatestAllByWine() {
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        WineTypeModel wineTypeModel = new WineTypeModel(1, "Rose", 150.0, currentTimestamp, currentTimestamp);

        assertTrue(bottledWineStorageService.getLatestAllByWine(wineTypeModel) instanceof ObservableList<BottledWineStorageModel>);
        assertNotNull(bottledWineStorageService.getAll());
    }
}