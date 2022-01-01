package bg.tu_varna.sit.vinarna.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrapeCategoryModelTest {

    private GrapeCategoryModel grapeCategoryModel;
    private final Timestamp currentTimestamp = new Timestamp(new Date().getTime());

    @BeforeEach
    void setUp() {
        grapeCategoryModel = new GrapeCategoryModel(1, "Category", currentTimestamp, currentTimestamp);
    }

    @Test
    void testGrapeCategoryModelData() {
        assertEquals(1, grapeCategoryModel.getId());
        assertEquals("Category", grapeCategoryModel.getCategory());
        assertEquals(currentTimestamp, grapeCategoryModel.getCreated_at());
        assertEquals(currentTimestamp, grapeCategoryModel.getUpdated_at());
    }
}