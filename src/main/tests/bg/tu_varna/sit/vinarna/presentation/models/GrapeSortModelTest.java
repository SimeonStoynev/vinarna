package bg.tu_varna.sit.vinarna.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrapeSortModelTest {

    private GrapeCategoryModel grapeCategoryModel;
    private GrapeSortModel grapeSortModel;
    private final Timestamp currentTimestamp = new Timestamp(new Date().getTime());

    @BeforeEach
    void setUp() {
        grapeCategoryModel = new GrapeCategoryModel(1, "Category", currentTimestamp, currentTimestamp);
        grapeSortModel = new GrapeSortModel(1, "Muskat", grapeCategoryModel, 2.5, currentTimestamp, currentTimestamp);
    }

    @Test
    void testGrapeSortModelData() {
        assertEquals(1, grapeSortModel.getId());
        assertEquals("Muskat", grapeSortModel.getName());
        assertEquals(grapeCategoryModel, grapeSortModel.getCategory());
        assertEquals(2.5, grapeSortModel.getWine_liters());
        assertEquals(currentTimestamp, grapeSortModel.getCreated_at());
        assertEquals(currentTimestamp, grapeSortModel.getUpdated_at());
    }

    @Test
    void testToString() {
        assertEquals(
            String.format("%s | %s", grapeSortModel.getName(), grapeSortModel.getCategory()),
            grapeSortModel.toString()
        );
    }
}