package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.BottledWineStorage;

import java.sql.Timestamp;

public class BottledWineStorageModel implements EntityModel<BottledWineStorage> {

    private int id;
    private BottleTypeModel bottle_type_id;
    private WineTypeModel wine_type_id;
    private int quantity_old;
    private int quantity;
    private int difference;
    private Timestamp created_at;
    private Timestamp updated_at;

    public BottledWineStorageModel() {}

    public BottledWineStorageModel(int id, BottleTypeModel bottle_type_id, WineTypeModel wine_type_id, int quantity_old, int quantity, int difference, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.bottle_type_id = bottle_type_id;
        this.wine_type_id = wine_type_id;
        this.quantity_old = quantity_old;
        this.quantity = quantity;
        this.difference = difference;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public BottledWineStorageModel(BottledWineStorageModel bottledWineStorage) {
        this.id = bottledWineStorage.getId();
        this.bottle_type_id = bottledWineStorage.getBottle_type_id();
        this.wine_type_id = bottledWineStorage.getWine_type_id();
        this.quantity_old = bottledWineStorage.getQuantity_old();
        this.quantity = bottledWineStorage.getQuantity();
        this.difference = bottledWineStorage.getDifference();
        this.created_at = bottledWineStorage.getCreated_at();
        this.updated_at = bottledWineStorage.getUpdated_at();
    }

    public BottledWineStorageModel(BottledWineStorage bottledWineStorage) {
        this.id = bottledWineStorage.getId();
        this.bottle_type_id = new BottleTypeModel(bottledWineStorage.getBottle_type_id());
        this.wine_type_id = new WineTypeModel(bottledWineStorage.getWine_type_id());
        this.quantity_old = bottledWineStorage.getQuantity_old();
        this.quantity = bottledWineStorage.getQuantity();
        this.difference = bottledWineStorage.getDifference();
        this.created_at = bottledWineStorage.getCreated_at();
        this.updated_at = bottledWineStorage.getUpdated_at();
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

    public WineTypeModel getWine_type_id() {
        return wine_type_id;
    }

    public void setWine_type_id(WineTypeModel wine_type_id) {
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
    public String toString() {
        return "BottledWineStorageModel{" +
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


    @Override
    public BottledWineStorage toEntity() {
        BottledWineStorage temp = new BottledWineStorage();

        temp.setId(this.id);
        temp.setBottle_type_id(this.bottle_type_id.toEntity());
        temp.setWine_type_id(this.wine_type_id.toEntity());
        temp.setQuantity_old(this.quantity_old);
        temp.setDifference(this.difference);
        temp.setCreated_at(this.created_at);
        temp.setUpdated_at(this.updated_at);

        return temp;
    }
}
