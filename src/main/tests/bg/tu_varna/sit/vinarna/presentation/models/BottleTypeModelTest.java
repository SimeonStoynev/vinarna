package bg.tu_varna.sit.vinarna.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BottleTypeModelTest {

    private BottleTypeModel bottleTypeModel;
    private final Timestamp currentTimestamp = new Timestamp(new Date().getTime());

    @BeforeEach
    void setUp() {
        bottleTypeModel = new BottleTypeModel(1, 375.0, currentTimestamp, currentTimestamp);
    }

    @Test
    void testBottleTypeModelData() {
        assertEquals(1, bottleTypeModel.getId());
        assertEquals(375.0, bottleTypeModel.getCapacity());
        assertEquals(currentTimestamp, bottleTypeModel.getCreated_at());
        assertEquals(currentTimestamp, bottleTypeModel.getUpdated_at());
    }
}