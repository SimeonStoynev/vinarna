package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.WineRecipe;
import bg.tu_varna.sit.vinarna.data.repositories.WineRecipeRepository;
import bg.tu_varna.sit.vinarna.presentation.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class WineRecipeService {
    private final WineRecipeRepository repository = WineRecipeRepository.getInstance();

    public static WineRecipeService getInstance() {
        return WineRecipeService.WineRecipeServiceHolder.INSTANCE;
    }

    private static class WineRecipeServiceHolder {
        public static final WineRecipeService INSTANCE = new WineRecipeService();
    }

    public ObservableList<WineRecipeModel> getAllRecipes() {
        List<WineRecipe> wineRecipes = repository.getAll();
        return FXCollections.observableList(
                wineRecipes.stream().map(w -> new WineRecipeModel(
                        w.getId(),
                        new WineTypeModel(w.getWine_type_id()),
                        new GrapeSortModel(w.getGrape_sort_id()),
                        w.getQuantity(),
                        w.getCreated_at(),
                        w.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }

    public ObservableList<WineRecipeModel> getAllRecipesByWineType(WineTypeModel wineType) {
        List<WineRecipe> wineRecipes = repository.getAllByWineId(wineType.getId());
        return FXCollections.observableList(
                wineRecipes.stream().map(w -> new WineRecipeModel(
                        w.getId(),
                        new WineTypeModel(w.getWine_type_id()),
                        new GrapeSortModel(w.getGrape_sort_id()),
                        w.getQuantity(),
                        w.getCreated_at(),
                        w.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }

    public int addWineRecipe(WineRecipeModel wineRecipeModel) {
        WineRecipe wineRecipe = wineRecipeModel.toEntity();
        repository.save(wineRecipe);
        return wineRecipe.getId();
    }
}
