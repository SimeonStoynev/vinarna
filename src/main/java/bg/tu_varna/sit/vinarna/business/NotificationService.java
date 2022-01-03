package bg.tu_varna.sit.vinarna.business;

import bg.tu_varna.sit.vinarna.data.entities.Notification;
import bg.tu_varna.sit.vinarna.data.repositories.NotificationRepository;
import bg.tu_varna.sit.vinarna.presentation.models.NotificationModel;
import bg.tu_varna.sit.vinarna.presentation.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationService {
    private static final Logger log = Logger.getLogger(NotificationService.class);

    private final NotificationRepository repository = NotificationRepository.getInstance();

    public static NotificationService getInstance() {
        return NotificationService.NotificationHolder.INSTANCE;
    }

    private static class NotificationHolder {
        public static final NotificationService INSTANCE = new NotificationService();
    }

    UserService userService = UserService.getInstance();

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

    public int addNotification(NotificationModel notification) {
        Notification notificationEntity = notification.toEntity();
        repository.save(notificationEntity);
        return notificationEntity.getId();
    }

    public void notifyUsers(String text) {
        ObservableList<UserModel> users = userService.getAllUser();

        for(UserModel user : users) {
            NotificationModel notification = new NotificationModel();
            notification.setId(0);
            notification.setUser_id(user);
            notification.setText(text);
            notification.setSeen(false);

            Date date = new Date();
            notification.setCreated_at(new Timestamp(date.getTime()));
            notification.setUpdated_at(new Timestamp(date.getTime()));

            addNotification(notification);

            try {
                Socket socket = new Socket("127.0.0.1", 5555);
                OutputStream output = socket.getOutputStream();

                String smsText = user.getPhone() + "/|/" + text;
                byte[] data = smsText.getBytes(StandardCharsets.UTF_8);
                output.write(data);
                socket.close();
            } catch (Exception ex) {
                log.error("TCP Send SMS Error: " + ex.getMessage());
            }

        }
    }
}
