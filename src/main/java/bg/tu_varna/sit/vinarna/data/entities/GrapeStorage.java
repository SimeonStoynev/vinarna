package bg.tu_varna.sit.vinarna.data.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Table(name = "grapes_storage")
@Entity
public class GrapeStorage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "sort_id", nullable = false)
    private Long sort_id;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getSort_id() {
        return sort_id;
    }

    public void setSort_id(Long sort_id) {
        this.sort_id = sort_id;
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
        if (o == null || getClass() != o.getClass()) return false;

        GrapeStorage that = (GrapeStorage) o;

        return Objects.equals(id, that.id)
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(sort_id, that.sort_id)
                && Objects.equals(created_at, that.created_at)
                && Objects.equals(updated_at, that.updated_at);
    }

    @Override
    public String toString() {
        return "GrapeStorage{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", sort_id=" + sort_id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}