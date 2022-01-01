package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.GrapeSort;
import bg.tu_varna.sit.vinarna.data.entities.GrapeStorage;
import bg.tu_varna.sit.vinarna.data.repositories.GrapeStorageRepository;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeSortModel;
import bg.tu_varna.sit.vinarna.presentation.models.GrapeStorageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.util.Date;
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

    public ObservableList<GrapeStorageModel> getLatestAll() {
        List<GrapeStorage> sorts = repository.getLatestAll();
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

    public int addStorage(GrapeStorageModel grapeStorage) {
        GrapeStorage grapeStorageEntity = grapeStorage.toEntity();
        repository.save(grapeStorageEntity);
        return grapeStorageEntity.getId();
    }

    public GrapeStorageModel getLastRowByGrapeSortId(int grapeSort_id) {
        GrapeStorage storage = repository.getLastByGrapeSortId(grapeSort_id);
        return (storage == null) ? null : new GrapeStorageModel(storage);
    }

    public GrapeStorageModel addGrapeQuantity(GrapeStorageModel grapeStorage, Double quantity) {
        GrapeStorageModel newStorage = new GrapeStorageModel();

        Double oldQuantity = grapeStorage.getQuantity();
        Double newQuantity = grapeStorage.getQuantity() + quantity;
        Double difference = quantity;

        newStorage.setId(0);
        newStorage.setQuantity_old(oldQuantity);
        newStorage.setQuantity(newQuantity);
        newStorage.setDifference(difference);
        newStorage.setSort(grapeStorage.getSort());

        Date date = new Date();
        newStorage.setCreated_at(new Timestamp(date.getTime()));
        newStorage.setUpdated_at(new Timestamp(date.getTime()));

        addStorage(newStorage);

        return newStorage;
    }

}
