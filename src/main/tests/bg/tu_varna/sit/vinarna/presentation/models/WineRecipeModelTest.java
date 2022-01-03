package bg.tu_varna.sit.vinarna.presentation.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WineRecipeModelTest {

    private WineTypeModel wineTypeModel;
    private GrapeSortModel grapeSortModel;
    private WineRecipeModel wineRecipeModel;
    private final Timestamp currentTimestamp = new Timestamp(new Date().getTime());

    @BeforeEach
    void setUp() {
        GrapeCategoryModel grapeCategoryModel = new GrapeCategoryModel(1, "Category", currentTimestamp, currentTimestamp);
        grapeSortModel = new GrapeSortModel(1, "Muskat", grapeCategoryModel, 2.5, currentTimestamp, currentTimestamp);
        wineTypeModel = new WineTypeModel(1, "Rose", 175.0, currentTimestamp, currentTimestamp);
        wineRecipeModel = new WineRecipeModel(1, wineTypeModel, grapeSortModel, 50.0, currentTimestamp, currentTimestamp);
    }

    @Test
    void testWineRecipeModelData() {
        assertEquals(1, wineRecipeModel.getId());
        assertEquals(wineTypeModel, wineRecipeModel.getWine_type_id());
        assertEquals(grapeSortModel, wineRecipeModel.getGrape_sort_id());
        assertEquals(50.0, wineRecipeModel.getQuantity());
        assertEquals(currentTimestamp, wineRecipeModel.getCreated_at());
        assertEquals(currentTimestamp, wineRecipeModel.getUpdated_at());
    }

    @Test
    void testToString() {
        assertEquals(
            "WineRecipeModel{" +
            "id=" + wineRecipeModel.getId() +
            ", wine_type_id=" + wineRecipeModel.getWine_type_id() +
            ", grape_sort_id=" + wineRecipeModel.getGrape_sort_id() +
            ", quantity=" + wineRecipeModel.getQuantity() +
            ", created_at=" + wineRecipeModel.getCreated_at() +
            ", updated_at=" + wineRecipeModel.getUpdated_at() +
            '}',
            wineRecipeModel.toString()
        );
    }
}