package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.GrapeSort;
import bg.tu_varna.sit.vinarna.data.entities.User;
import bg.tu_varna.sit.vinarna.data.repositories.GrapeSortRepository;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeCategoryModel;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import bg.tu_varna.sit.vinarna.presentation.models.RoleModel;
import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class GrapeSortService {
    private final GrapeSortRepository repository = GrapeSortRepository.getInstance();

    public static GrapeSortService getInstance() {
        return GrapeSortService.GrapeSortServiceHolder.INSTANCE;
    }

    private static class GrapeSortServiceHolder {
        public static final GrapeSortService INSTANCE = new GrapeSortService();
    }

    public ObservableList<GrapeSortModel> getAllSorts() {
        List<GrapeSort> sorts = repository.getAll();
        return FXCollections.observableList(
                sorts.stream().map(g -> new GrapeSortModel(
                        g.getId(),
                        g.getName(),
                        new GrapeCategoryModel(g.getCategory()),
                        g.getCreated_at(),
                        g.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }
}
