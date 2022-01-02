package bg.tu_varna.sit.vinarna.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    void getLatestAllByWine() {
    }

    @Test
    void getLastByBottleAndWine() {
    }

    @Test
    void addStorage() {
    }
}