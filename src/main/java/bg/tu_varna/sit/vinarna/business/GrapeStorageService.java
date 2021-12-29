package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.GrapeStorage;
import bg.tu_varna.sit.vinarna.data.repositories.GrapeStorageRepository;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeStorageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class GrapeStorageService {
    private final GrapeStorageRepository repository = GrapeStorageRepository.getInstance();

    public static GrapeStorageService getInstance() {
        return GrapeStorageService.GrapeStorageHolder.INSTANCE;
    }

    private static class GrapeStorageHolder {
        public static final GrapeStorageService INSTANCE = new GrapeStorageService();
    }

    public ObservableList<GrapeStorageModel> getAll() {
        List<GrapeStorage> sorts = repository.getAll();
        return FXCollections.observableList(
                sorts.stream().map(g -> new GrapeStorageModel(
                        g.getId(),
                        g.getQuantity_old(),
                        g.getQuantity(),
                        g.getDifference(),
                        new GrapeSortModel(g.getSort()),
                        g.getCreated_at(),
                        g.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }
}
