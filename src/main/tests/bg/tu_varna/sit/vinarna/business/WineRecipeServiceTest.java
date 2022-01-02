package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.presentation.models.WineRecipeModel;
import bg.tu_varna.sit.vinarna.presentation.models.WineTypeModel;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WineRecipeServiceTest {

    private WineRecipeService wineRecipeService;

    @BeforeEach
    void setUp() {
        wineRecipeService = WineRecipeService.getInstance();
    }

    @Test
    void getInstance() {
        assertTrue(wineRecipeService instanceof WineRecipeService);
        assertNotNull(wineRecipeService);
    }

    @Test
    void getAllRecipes() {
        assertTrue(wineRecipeService.getAllRecipes() instanceof ObservableList<WineRecipeModel>);
        assertNotNull(wineRecipeService.getAllRecipes());
    }

    @Test
    void getAllRecipesByWineType() {
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        WineTypeModel wineTypeModel  = new WineTypeModel(1, "Rose", 150.0, currentTimestamp, currentTimestamp);

        assertTrue(wineRecipeService.getAllRecipesByWineType(wineTypeModel) instanceof ObservableList<WineRecipeModel>);
        assertNotNull(wineRecipeService.getAllRecipesByWineType(wineTypeModel));
    }
}