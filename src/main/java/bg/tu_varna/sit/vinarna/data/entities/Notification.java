package bg.tu_varna.sit.vinarna.data.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Table(name = "notifications")
@Entity
public class Notification {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "seen", nullable = false)
    private boolean seen;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
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
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return id == that.id && seen == that.seen && user_id.equals(that.user_id) && text.equals(that.text) && created_at.equals(that.created_at) && updated_at.equals(that.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, text, seen, created_at, updated_at);
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", text='" + text + '\'' +
                ", seen=" + seen +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
