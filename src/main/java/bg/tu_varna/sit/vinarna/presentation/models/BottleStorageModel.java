package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.BottleStorage;

import java.sql.Timestamp;

public class BottleStorageModel implements EntityModel<BottleStorage> {

    int id;
    BottleTypeModel bottle_type_id;
    int quantity_old;
    int quantity;
    int difference;
    Timestamp created_at;
    Timestamp updated_at;

    public BottleStorageModel(int id, BottleTypeModel bottle_type_id, int quantity_old, int quantity, int difference, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.bottle_type_id = bottle_type_id;
        this.quantity_old = quantity_old;
        this.quantity = quantity;
        this.difference = difference;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public BottleStorageModel(BottleStorageModel bottleStorage) {
        this.id = bottleStorage.getId();
        this.bottle_type_id = bottleStorage.getBottle_type_id();
        this.quantity_old = bottleStorage.getQuantity_old();
        this.quantity = bottleStorage.getQuantity();
        this.difference = bottleStorage.getDifference();
        this.created_at = bottleStorage.getCreated_at();
        this.updated_at = bottleStorage.getUpdated_at();
    }

    public BottleStorageModel(BottleStorage bottleStorage) {
        this.id = bottleStorage.getId();
        this.bottle_type_id = new BottleTypeModel(bottleStorage.getBottle_type_id());
        this.quantity_old = bottleStorage.getQuantity_old();
        this.quantity = bottleStorage.getQuantity();
        this.difference = bottleStorage.getDifference();
        this.created_at = bottleStorage.getCreated_at();
        this.updated_at = bottleStorage.getUpdated_at();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BottleTypeModel getBottle_type_id() {
        return bottle_type_id;
    }

    public void setBottle_type_id(BottleTypeModel bottle_type_id) {
        this.bottle_type_id = bottle_type_id;
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
    public String toString() {
        return "BottleStorageModel{" +
                "id=" + id +
                ", bottle_type_id=" + bottle_type_id +
                ", quantity_old=" + quantity_old +
                ", quantity=" + quantity +
                ", difference=" + difference +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }


    @Override
    public BottleStorage toEntity() {
        BottleStorage temp = new BottleStorage();

        temp.setId(this.id);
        temp.setBottle_type_id(this.bottle_type_id.toEntity());
        temp.setQuantity_old(this.quantity_old);
        temp.setQuantity(this.quantity);
        temp.setDifference(this.difference);
        temp.setCreated_at(this.created_at);
        temp.setUpdated_at(this.updated_at);

        return temp;
    }
}
