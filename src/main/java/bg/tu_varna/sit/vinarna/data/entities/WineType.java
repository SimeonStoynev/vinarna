package bg.tu_varna.sit.vinarna.data.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Table(name = "wine_types")
@Entity
public class WineType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "produced", nullable = false)
    private Double produced;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getProduced() {
        return produced;
    }

    public void setProduced(Double produced) {
        this.produced = produced;
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
        if (!(o instanceof WineType)) return false;
        WineType wineType = (WineType) o;
        return id == wineType.id && name.equals(wineType.name) && produced.equals(wineType.produced) && created_at.equals(wineType.created_at) && updated_at.equals(wineType.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, produced, created_at, updated_at);
    }

    @Override
    public String toString() {
        return "WineType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", produced=" + produced +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
