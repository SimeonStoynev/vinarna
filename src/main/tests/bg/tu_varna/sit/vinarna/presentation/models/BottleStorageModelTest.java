package bg.tu_varna.sit.vinarna.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BottleStorageModelTest {

    private BottleTypeModel bottleTypeModel;
    private BottleStorageModel bottleStorageModel;
    private final Timestamp currentTimestamp = new Timestamp(new Date().getTime());

    @BeforeEach
    void setUp() {
        bottleTypeModel = new BottleTypeModel(1, 750.0, currentTimestamp, currentTimestamp);
        bottleStorageModel = new BottleStorageModel(1, bottleTypeModel, 60, 110, 50, currentTimestamp, currentTimestamp);
    }

    @Test
    void testBottleStorageModelData() {
        assertEquals(1, bottleStorageModel.getId());
        assertEquals(bottleTypeModel, bottleStorageModel.getBottle_type_id());
        assertEquals(60, bottleStorageModel.getQuantity_old());
        assertEquals(110, bottleStorageModel.getQuantity());
        assertEquals(50, bottleStorageModel.getDifference());
        assertEquals(currentTimestamp, bottleStorageModel.getCreated_at());
        assertEquals(currentTimestamp, bottleStorageModel.getUpdated_at());
    }

    @Test
    void testToString() {
        assertEquals(
        "BottleStorageModel{" +
                "id=" + bottleStorageModel.getId() +
                ", bottle_type_id=" + bottleStorageModel.getBottle_type_id() +
                ", quantity_old=" + bottleStorageModel.getQuantity_old() +
                ", quantity=" + bottleStorageModel.getQuantity() +
                ", difference=" + bottleStorageModel.getDifference() +
                ", created_at=" + bottleStorageModel.getCreated_at() +
                ", updated_at=" + bottleStorageModel.getUpdated_at() +
                '}',
                bottleStorageModel.toString()
        );
    }
}