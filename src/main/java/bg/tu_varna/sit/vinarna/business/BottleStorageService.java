package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.BottleStorage;
import bg.tu_varna.sit.vinarna.data.repositories.BottleStorageRepository;
import bg.tu_varna.sit.vinarna.presentation.models.BottleStorageModel;
import bg.tu_varna.sit.vinarna.presentation.models.BottleTypeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BottleStorageService {
    private final BottleStorageRepository repository = BottleStorageRepository.getInstance();

    public static BottleStorageService getInstance() {
        return BottleStorageService.BottleStorageHolder.INSTANCE;
    }

    private static class BottleStorageHolder {
        public static final BottleStorageService INSTANCE = new BottleStorageService();
    }

    public ObservableList<BottleStorageModel> getAll() {
        List<BottleStorage> bottles = repository.getAll();
        return FXCollections.observableList(
                bottles.stream().map(b -> new BottleStorageModel(
                        b.getId(),
                        new BottleTypeModel(b.getBottle_type_id()),
                        b.getQuantity_old(),
                        b.getQuantity(),
                        b.getDifference(),
                        b.getCreated_at(),
                        b.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }

    public ObservableList<BottleStorageModel> getLatestAll() {
        List<BottleStorage> bottles = repository.getLatestAll();
        return FXCollections.observableList(
                bottles.stream().map(b -> new BottleStorageModel(
                        b.getId(),
                        new BottleTypeModel(b.getBottle_type_id()),
                        b.getQuantity_old(),
                        b.getQuantity(),
                        b.getDifference(),
                        b.getCreated_at(),
                        b.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }

    public ObservableList<BottleStorageModel> getAllByBottle(BottleTypeModel bottle) {
        List<BottleStorage> bottles = repository.getAllByBottle(bottle.toEntity());
        return FXCollections.observableList(
                bottles.stream().map(b -> new BottleStorageModel(
                        b.getId(),
                        new BottleTypeModel(b.getBottle_type_id()),
                        b.getQuantity_old(),
                        b.getQuantity(),
                        b.getDifference(),
                        b.getCreated_at(),
                        b.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }

    public ObservableList<BottleStorageModel> getAllByBottleAndPeriod(BottleTypeModel bottle, LocalDate startDate, LocalDate endDate) {
        List<BottleStorage> bottles = repository.getAllByBottleAndPeriod(bottle.toEntity(), startDate, endDate);
        return FXCollections.observableList(
                bottles.stream().map(b -> new BottleStorageModel(
                        b.getId(),
                        new BottleTypeModel(b.getBottle_type_id()),
                        b.getQuantity_old(),
                        b.getQuantity(),
                        b.getDifference(),
                        b.getCreated_at(),
                        b.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }

    public BottleStorageModel getLastByBottle(BottleTypeModel bottle) {
        BottleStorage storage = repository.getLastByBottle(bottle.toEntity());
        return (storage == null) ? null : new BottleStorageModel(storage);
    }

    public int addStorage(BottleStorageModel bottleStorage) {
        BottleStorage bottleStorageEntity = bottleStorage.toEntity();
        repository.save(bottleStorageEntity);
        return bottleStorageEntity.getId();
    }
}
