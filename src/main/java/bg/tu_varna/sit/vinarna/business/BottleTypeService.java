package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.BottleType;
import bg.tu_varna.sit.vinarna.data.repositories.BottleTypeRepository;
import bg.tu_varna.sit.vinarna.presentation.models.BottleTypeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class BottleTypeService {
    private final BottleTypeRepository repository = BottleTypeRepository.getInstance();

    public static BottleTypeService getInstance() {
        return BottleTypeService.BottleTypeHolder.INSTANCE;
    }

    private static class BottleTypeHolder {
        public static final BottleTypeService INSTANCE = new BottleTypeService();
    }

    public ObservableList<BottleTypeModel> getAll() {
        List<BottleType> bottles = repository.getAll();
        return FXCollections.observableList(
                bottles.stream().map(g -> new BottleTypeModel(
                        g.getId(),
                        g.getCapacity(),
                        g.getCreated_at(),
                        g.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }

    public boolean isBottleCapacityExists(Double capacity) {
        return repository.getByCapacity(capacity) != null;
    }

    public int addBottleType(BottleTypeModel bottleTypeModel) {
        BottleType bottleType = bottleTypeModel.toEntity();
        repository.save(bottleType);
        return bottleType.getId();
    }

    public int updateBottleType(BottleTypeModel bottleTypeModel) {
        repository.update(bottleTypeModel.toEntity());
        return 0;
    }
}
