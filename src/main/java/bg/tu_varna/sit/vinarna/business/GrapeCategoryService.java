package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.GrapeCategory;
import bg.tu_varna.sit.vinarna.data.repositories.GrapeCategoryRepository;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeCategoryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class GrapeCategoryService {
    private final GrapeCategoryRepository repository = GrapeCategoryRepository.getInstance();

    public static GrapeCategoryService getInstance() {
        return GrapeCategoryService.GrapeCategoryServiceHolder.INSTANCE;
    }

    private static class GrapeCategoryServiceHolder {
        public static final GrapeCategoryService INSTANCE = new GrapeCategoryService();
    }

    public ObservableList<GrapeCategoryModel> getAll() {
        List<GrapeCategory> sorts = repository.getAll();
        return FXCollections.observableList(
                sorts.stream().map(g -> new GrapeCategoryModel(
                        g.getId(),
                        g.getCategory(),
                        g.getCreated_at(),
                        g.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }
}
