package bg.tu_varna.sit.vinarna.data.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Table(name = "bottled_wine_storage")
@Entity
public class BottledWineStorage {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "bottle_type_id", nullable = false)
    private BottleType bottle_type_id;

    @ManyToOne
    @JoinColumn(name = "wine_type_id", nullable = false)
    private WineType wine_type_id;

    @Column(name = "quantity_old", nullable = false)
    private int quantity_old;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "difference", nullable = false)
    private int difference;

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

    public BottleType getBottle_type_id() {
        return bottle_type_id;
    }

    public void setBottle_type_id(BottleType bottle_type_id) {
        this.bottle_type_id = bottle_type_id;
    }

    public WineType getWine_type_id() {
        return wine_type_id;
    }

    public void setWine_type_id(WineType wine_type_id) {
        this.wine_type_id = wine_type_id;
    }

    public int getQuantity_old() {
        return quantity_old;
    }

    public void setQuantity_old(int quantity_old) {
        this.quantity_old = quantity_old;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
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
        if (!(o instanceof BottledWineStorage)) return false;
        BottledWineStorage that = (BottledWineStorage) o;
        return id == that.id && quantity_old == that.quantity_old && quantity == that.quantity && difference == that.difference && bottle_type_id.equals(that.bottle_type_id) && wine_type_id.equals(that.wine_type_id) && created_at.equals(that.created_at) && updated_at.equals(that.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bottle_type_id, wine_type_id, quantity_old, quantity, difference, created_at, updated_at);
    }

    @Override
    public String toString() {
        return "BottledWineStorage{" +
                "id=" + id +
                ", bottle_type_id=" + bottle_type_id +
                ", wine_type_id=" + wine_type_id +
                ", quantity_old=" + quantity_old +
                ", quantity=" + quantity +
                ", difference=" + difference +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
