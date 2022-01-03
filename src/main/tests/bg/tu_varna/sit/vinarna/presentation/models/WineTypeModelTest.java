package bg.tu_varna.sit.vinarna.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WineTypeModelTest {
    private WineTypeModel wineTypeModel;
    private final Timestamp currentTimestamp = new Timestamp(new Date().getTime());

    @BeforeEach
    void setUp() {
        wineTypeModel = new WineTypeModel(1, "Rose", 150.0, currentTimestamp, currentTimestamp);
    }

    @Test
    void testWineTypeModelData() {
        assertEquals(1, wineTypeModel.getId());
        assertEquals("Rose", wineTypeModel.getName());
        assertEquals(150.0, wineTypeModel.getProduced());
        assertEquals(currentTimestamp, wineTypeModel.getCreated_at());
        assertEquals(currentTimestamp, wineTypeModel.getUpdated_at());
    }

    @Test
    void testToString() {
        assertEquals(String.format("%s", wineTypeModel.getName()), wineTypeModel.toString());
    }
}