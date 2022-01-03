package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.Notification;
import bg.tu_varna.sit.vinarna.data.repositories.NotificationRepository;
import bg.tu_varna.sit.vinarna.presentation.models.NotificationModel;
import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationService {
    private final NotificationRepository repository = NotificationRepository.getInstance();

    public static NotificationService getInstance() {
        return NotificationService.NotificationHolder.INSTANCE;
    }

    private static class NotificationHolder {
        public static final NotificationService INSTANCE = new NotificationService();
    }

    public ObservableList<NotificationModel> getDescByUser(UserModel user, int limit) {
        List<Notification> notifications = repository.getDescByUser(user.toEntity(), limit);
        return FXCollections.observableList(
                notifications.stream().map(n -> new NotificationModel(
                        n.getId(),
                        new UserModel(n.getUser_id()),
                        n.getText(),
                        n.isSeen(),
                        n.getCreated_at(),
                        n.getUpdated_at()
                )).collect(Collectors.toList())
        );
    }

    public int getUnseenCountByUser(UserModel user) {
        List<Notification> notifications = repository.getAllUnseenByUser(user.toEntity());
        return notifications.size();
    }

    public void setSeenByUser(UserModel user) {
        repository.setSeenByUser(user.toEntity());
    }
}
