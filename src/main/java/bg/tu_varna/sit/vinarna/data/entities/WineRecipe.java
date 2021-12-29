package bg.tu_varna.sit.vinarna.data.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Table(name = "wine_recipes")
@Entity
public class WineRecipe implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne
    @Column(name = "wine_type_id", nullable = false)
    private WineType wine_type_id;

    @OneToOne
    @Column(name = "grape_sort_id", nullable = false)
    private GrapeSort grape_sort_id;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

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

    public WineType getWine_type_id() {
        return wine_type_id;
    }

    public void setWine_type_id(WineType wine_type_id) {
        this.wine_type_id = wine_type_id;
    }

    public GrapeSort getGrape_sort_id() {
        return grape_sort_id;
    }

    public void setGrape_sort_id(GrapeSort grape_sort_id) {
        this.grape_sort_id = grape_sort_id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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
        WineRecipe that = (WineRecipe) o;
        return Objects.equals(id, that.id) && Objects.equals(wine_type_id, that.wine_type_id) && Objects.equals(grape_sort_id, that.grape_sort_id) && Objects.equals(quantity, that.quantity) && Objects.equals(created_at, that.created_at) && Objects.equals(updated_at, that.updated_at);
    }

    @Override
    public String toString() {
        return "WineRecipe{" +
                "id=" + id +
                ", wine_type_id=" + wine_type_id +
                ", grape_sort_id=" + grape_sort_id +
                ", quantity=" + quantity +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
