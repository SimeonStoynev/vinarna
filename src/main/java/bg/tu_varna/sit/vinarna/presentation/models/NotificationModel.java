package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.Notification;

import java.sql.Timestamp;
import java.util.Objects;

public class NotificationModel implements EntityModel<Notification> {

    private int id;
    private UserModel user_id;
    private String text;
    private boolean seen;
    private Timestamp created_at;
    private Timestamp updated_at;

    public NotificationModel() {}

    public NotificationModel(int id, UserModel user_id, String text, boolean seen, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.text = text;
        this.seen = seen;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public NotificationModel(NotificationModel notification) {
        this.id = notification.getId();
        this.user_id = notification.getUser_id();
        this.text = notification.getText();
        this.seen = notification.isSeen();
        this.created_at = notification.getCreated_at();
        this.updated_at = notification.getUpdated_at();
    }

    public NotificationModel(Notification notification) {
        this.id = notification.getId();
        this.user_id = new UserModel(notification.getUser_id());
        this.text = notification.getText();
        this.seen = notification.isSeen();
        this.created_at = notification.getCreated_at();
        this.updated_at = notification.getUpdated_at();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser_id() {
        return user_id;
    }

    public void setUser_id(UserModel user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationModel)) return false;
        NotificationModel that = (NotificationModel) o;
        return id == that.id && seen == that.seen && user_id.equals(that.user_id) && created_at.equals(that.created_at) && updated_at.equals(that.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, seen, created_at, updated_at);
    }

    @Override
    public String toString() {
        return this.text;
    }

    @Override
    public Notification toEntity() {
        Notification temp = new Notification();

        temp.setId(this.id);
        temp.setUser_id(this.getUser_id().toEntity());
        temp.setText(this.text);
        temp.setSeen(this.seen);
        temp.setCreated_at(this.getCreated_at());
        temp.setUpdated_at(this.getUpdated_at());

        return temp;
    }
}
