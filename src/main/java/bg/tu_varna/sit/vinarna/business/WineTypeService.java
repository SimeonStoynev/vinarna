package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.User;
import bg.tu_varna.sit.vinarna.data.entities.WineType;
import bg.tu_varna.sit.vinarna.data.repositories.WineTypeRepository;
import bg.tu_varna.sit.vinarna.presentation.models.RoleModel;
import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
import bg.tu_varna.sit.vinarna.presentation.models.WineTypeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class WineTypeService {
    private final WineTypeRepository repository = WineTypeRepository.getInstance();

    public static WineTypeService getInstance() {
        return WineTypeService.WineTypeServiceHolder.INSTANCE;
    }

    private static class WineTypeServiceHolder {
        public static final WineTypeService INSTANCE = new WineTypeService();
    }

    public ObservableList<WineTypeModel> getAllWineTypes() {
        List<WineType> wineTypes = repository.getAll();
        return FXCollections.observableList(
                wineTypes.stream().map(w -> new WineTypeModel(
                        w.getId(),
                        w.getName(),
                        w.getCreated_at(),
                        w.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }
}
