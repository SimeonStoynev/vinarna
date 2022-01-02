package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.WineType;

import java.sql.Timestamp;
import java.util.Objects;

public class WineTypeModel implements EntityModel<WineType> {
    private int id;
    private String name;
    private Double produced;
    private Timestamp created_at;
    private Timestamp updated_at;

    public WineTypeModel() {}

    public WineTypeModel(int id, String name, Double produced, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.produced = produced;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public WineTypeModel(WineTypeModel wineType) {
        this.id = wineType.getId();
        this.name = wineType.getName();
        this.produced = wineType.getProduced();
        this.created_at = wineType.getCreated_at();
        this.updated_at = wineType.getUpdated_at();
    }

    public WineTypeModel(WineType wineType) {
        this.id = wineType.getId();
        this.name = wineType.getName();
        this.produced = wineType.getProduced();
        this.created_at = wineType.getCreated_at();
        this.updated_at = wineType.getUpdated_at();
    }

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
        if (!(o instanceof WineTypeModel)) return false;
        WineTypeModel that = (WineTypeModel) o;
        return id == that.id && name.equals(that.name) && produced.equals(that.produced) && created_at.equals(that.created_at) && updated_at.equals(that.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, produced, created_at, updated_at);
    }

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }

    @Override
    public WineType toEntity() {
        WineType temp = new WineType();

        temp.setId(this.id);
        temp.setName(this.name);
        temp.setProduced(this.produced);
        temp.setCreated_at(this.created_at);
        temp.setUpdated_at(this.updated_at);

        return temp;
    }
}
