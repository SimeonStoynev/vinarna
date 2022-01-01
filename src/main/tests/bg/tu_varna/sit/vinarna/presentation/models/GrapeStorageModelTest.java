package bg.tu_varna.sit.vinarna.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrapeStorageModelTest {

    private GrapeSortModel grapeSortModel;
    private GrapeStorageModel grapeStorageModel;
    private final Timestamp currentTimestamp = new Timestamp(new Date().getTime());

    @BeforeEach
    void setUp() {
        GrapeCategoryModel grapeCategoryModel = new GrapeCategoryModel(1, "Category", currentTimestamp, currentTimestamp);
        grapeSortModel = new GrapeSortModel(1, "Muskat", grapeCategoryModel, 2.5, currentTimestamp, currentTimestamp);
        grapeStorageModel = new GrapeStorageModel(1, 60.0, 110.0, 50.0, grapeSortModel, currentTimestamp, currentTimestamp);
    }

    @Test
    void testGrapeStorageModelData() {
        assertEquals(1, grapeStorageModel.getId());
        assertEquals(60.0, grapeStorageModel.getQuantity_old());
        assertEquals(110.0, grapeStorageModel.getQuantity());
        assertEquals(50.0, grapeStorageModel.getDifference());
        assertEquals(grapeSortModel, grapeStorageModel.getSort());
        assertEquals(currentTimestamp, grapeSortModel.getCreated_at());
        assertEquals(currentTimestamp, grapeSortModel.getUpdated_at());
    }
}