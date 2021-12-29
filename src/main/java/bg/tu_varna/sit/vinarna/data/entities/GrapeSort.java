package bg.tu_varna.sit.vinarna.data.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Table(name = "grape_sort")
@Entity
public class GrapeSort implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private GrapeCategory category;

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

    public GrapeCategory getCategory() {
        return category;
    }

    public void setCategory_id(GrapeCategory category) {
        this.category = category;
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
        GrapeSort grapeSort = (GrapeSort) o;

        // !!!!!!!!!!!!!! Must be edited !!!!!!!!!!!!!!!!!
        //
        //
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return Objects.equals(id, grapeSort.id) && Objects.equals(name, grapeSort.name) && Objects.equals(created_at, grapeSort.created_at) && Objects.equals(updated_at, grapeSort.updated_at);
        //return Objects.equals(id, grapeSort.id) && Objects.equals(name, grapeSort.name) && Objects.equals(category_id, grapeSort.category_id) && Objects.equals(created_at, grapeSort.created_at) && Objects.equals(updated_at, grapeSort.updated_at);
    }

    @Override
    public String toString() {
        return "GrapeSort{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category.getCategory() +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
