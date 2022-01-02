package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.GrapeStorage;

import java.sql.Timestamp;

public class GrapeStorageModel implements EntityModel<GrapeStorage> {

    private int id;
    private Double quantity_old;
    private Double quantity;
    private Double difference;
    private GrapeSortModel sort;
    private Timestamp created_at;
    private Timestamp updated_at;

    public GrapeStorageModel() {}

    public GrapeStorageModel(int id, Double quantity_old, Double quantity, Double difference, GrapeSortModel sort, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.quantity_old = quantity_old;
        this.quantity = quantity;
        this.difference = difference;
        this.sort = sort;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public GrapeStorageModel (GrapeStorage grapeStorage) {
        this.id = grapeStorage.getId();
        this.quantity_old = grapeStorage.getQuantity_old();
        this.quantity = grapeStorage.getQuantity();
        this.difference = grapeStorage.getDifference();
        this.sort = new GrapeSortModel(grapeStorage.getSort());
        this.created_at = grapeStorage.getCreated_at();
        this.updated_at = grapeStorage.getUpdated_at();
    }

    public GrapeStorageModel(GrapeStorageModel storageModel) {
        this.id = storageModel.getId();
        this.quantity_old = storageModel.getQuantity_old();
        this.quantity = storageModel.getQuantity();
        this.difference = storageModel.getDifference();
        this.sort = storageModel.getSort();
        this.created_at = storageModel.getCreated_at();
        this.updated_at = storageModel.getUpdated_at();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getQuantity_old() {
        return quantity_old;
    }

    public void setQuantity_old(Double quantity_old) {
        this.quantity_old = quantity_old;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }

    public GrapeSortModel getSort() {
        return sort;
    }

    public void setSort(GrapeSortModel sort) {
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
    public String toString() {
        return "GrapeStorageModel{" +
                "id=" + id +
                ", quantity_old=" + quantity_old +
                ", quantity=" + quantity +
                ", difference=" + difference +
                ", sort=" + sort +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    @Override
    public GrapeStorage toEntity() {
        GrapeStorage temp = new GrapeStorage();

        temp.setId(this.id);
        temp.setQuantity_old(this.quantity_old);
        temp.setQuantity(this.quantity);
        temp.setDifference(this.difference);
        temp.setSort(this.sort.toEntity());
        temp.setCreated_at(this.created_at);
        temp.setUpdated_at(this.updated_at);

        return temp;
    }
}
