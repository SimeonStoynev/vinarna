package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.Role;
import bg.tu_varna.sit.vinarna.data.repositories.RoleRepository;
import bg.tu_varna.sit.vinarna.presentation.models.RoleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class RoleService {
    private final RoleRepository repository = RoleRepository.getInstance();

    public static RoleService getInstance() {
        return RoleService.RoleServiceHolder.INSTANCE;
    }

    private static class RoleServiceHolder {
        public static final RoleService INSTANCE = new RoleService();
    }

    public ObservableList<RoleModel> getAllRoles() {
        List<Role> roles = repository.getAll();
        return FXCollections.observableList(
                roles.stream().map(u -> new RoleModel(
                        u.getId(),
                        u.getName()
                )).collect(Collectors.toList())
        );
    }
}
