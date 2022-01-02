package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.BottleStorage;
import bg.tu_varna.sit.vinarna.data.entities.BottledWineStorage;
import bg.tu_varna.sit.vinarna.data.repositories.BottledWineStorageRepository;
import bg.tu_varna.sit.vinarna.presentation.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class BottledWineStorageService {
    private final BottledWineStorageRepository repository = BottledWineStorageRepository.getInstance();

    public static BottledWineStorageService getInstance() {
        return BottledWineStorageService.BottledWineStorageHolder.INSTANCE;
    }

    private static class BottledWineStorageHolder {
        public static final BottledWineStorageService INSTANCE = new BottledWineStorageService();
    }

    public ObservableList<BottledWineStorageModel> getAll() {
        List<BottledWineStorage> storage = repository.getAll();
        return FXCollections.observableList(
                storage.stream().map(g -> new BottledWineStorageModel(
                        g.getId(),
                        new BottleTypeModel(g.getBottle_type_id()),
                        new WineTypeModel(g.getWine_type_id()),
                        g.getQuantity_old(),
                        g.getQuantity(),
                        g.getDifference(),
                        g.getCreated_at(),
                        g.getUpdated_at()

                )).collect(Collectors.toList())
        );
    }

    public ObservableList<BottledWineStorageModel> getLatestAllByWine(WineTypeModel wine) {
        List<BottledWineStorage> storage = repository.getLatestAllByWine(wine.toEntity());
        return FXCollections.observableList(
                storage.stream().map(b -> new BottledWineStorageModel(
                        b.getId(),
                        new BottleTypeModel(b.getBottle_type_id()),
                        new WineTypeModel(b.getWine_type_id()),
                        b.getQuantity_old(),
                        b.getQuantity(),
                        b.getDifference(),
                        b.getCreated_at(),
                        b.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }

    public BottledWineStorageModel getLastByBottleAndWine(BottleTypeModel bottle, WineTypeModel wine) {
        BottledWineStorage storage = repository.getLastByBottleTypeAndWineType(bottle.toEntity(), wine.toEntity());
        return (storage == null) ? null : new BottledWineStorageModel(storage);
    }


    public int addStorage(BottledWineStorageModel storage) {
        BottledWineStorage storageEntity = storage.toEntity();
        repository.save(storageEntity);
        return storageEntity.getId();
    }
}
