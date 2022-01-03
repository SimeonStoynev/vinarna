package bg.tu_varna.sit.vinarna.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BottledWineStorageModelTest {

    private WineTypeModel wineTypeModel;
    private BottleTypeModel bottleTypeModel;
    private BottledWineStorageModel bottledWineStorageModel;
    private final Timestamp currentTimestamp = new Timestamp(new Date().getTime());

    @BeforeEach
    void setUp() {
        wineTypeModel = new WineTypeModel(1, "Rose", 150.0, currentTimestamp, currentTimestamp);
        bottleTypeModel = new BottleTypeModel(1, 375.0, currentTimestamp, currentTimestamp);
        bottledWineStorageModel = new BottledWineStorageModel(1, bottleTypeModel, wineTypeModel, 45, 100, 55, currentTimestamp, currentTimestamp);
    }

    @Test
    void testBottledWineStorageModelData() {
        assertEquals(1, bottledWineStorageModel.getId());
        assertEquals(bottleTypeModel, bottledWineStorageModel.getBottle_type_id());
        assertEquals(wineTypeModel, bottledWineStorageModel.getWine_type_id());
        assertEquals(45, bottledWineStorageModel.getQuantity_old());
        assertEquals(100, bottledWineStorageModel.getQuantity());
        assertEquals(55, bottledWineStorageModel.getDifference());
        assertEquals(currentTimestamp, bottledWineStorageModel.getCreated_at());
        assertEquals(currentTimestamp, bottledWineStorageModel.getUpdated_at());
    }

    @Test
    void testToString() {
        assertEquals(
        "BottledWineStorageModel{" +
                "id=" + bottledWineStorageModel.getId() +
                ", bottle_type_id=" + bottledWineStorageModel.getBottle_type_id() +
                ", wine_type_id=" + bottledWineStorageModel.getWine_type_id() +
                ", quantity_old=" + bottledWineStorageModel.getQuantity_old() +
                ", quantity=" + bottledWineStorageModel.getQuantity() +
                ", difference=" + bottledWineStorageModel.getDifference() +
                ", created_at=" + bottledWineStorageModel.getCreated_at() +
                ", updated_at=" + bottledWineStorageModel.getUpdated_at() +
                '}',
                bottledWineStorageModel.toString()
        );
    }
}