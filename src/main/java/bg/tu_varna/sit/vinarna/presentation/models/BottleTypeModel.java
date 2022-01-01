package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.BottleType;

import java.sql.Timestamp;

public class BottleTypeModel implements EntityModel<BottleType> {

    int id;
    Double capacity;
    Timestamp created_at;
    Timestamp updated_at;

    public BottleTypeModel() {}

    public BottleTypeModel(int id, Double capacity, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.capacity = capacity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public BottleTypeModel(BottleTypeModel bottleType) {
        this.id = bottleType.getId();
        this.capacity = bottleType.getCapacity();
        this.created_at = bottleType.getCreated_at();
        this.updated_at = bottleType.getUpdated_at();
    }

    public BottleTypeModel(BottleType bottleType) {
        this.id = bottleType.getId();
        this.capacity = bottleType.getCapacity();
        this.created_at = bottleType.getCreated_at();
        this.updated_at = bottleType.getUpdated_at();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
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
    public String toString() {
        return "BottleTypeModel{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    @Override
    public BottleType toEntity() {
        BottleType temp = new BottleType();

        temp.setId(this.id);
        temp.setCapacity(this.capacity);
        temp.setCreated_at(this.created_at);
        temp.setUpdated_at(this.updated_at);

        return temp;
    }
}
