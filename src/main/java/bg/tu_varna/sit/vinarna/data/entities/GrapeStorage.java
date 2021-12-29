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
    private int id;

    @Column(name = "quantity", nullable = false)
    private Double quantity;


    @ManyToOne
    @JoinColumn(name = "sort_id", nullable = false)
    private GrapeSort sort;


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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public GrapeSort getSort() {
        return sort;
    }

    public void setSort_id(GrapeSort sort) {
        this.sort = sort;
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
                && Objects.equals(sort, that.sort)
                && Objects.equals(created_at, that.created_at)
                && Objects.equals(updated_at, that.updated_at);
    }

    @Override
    public String toString() {
        return "GrapeStorage{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", sort_id=" + sort.toString() +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
