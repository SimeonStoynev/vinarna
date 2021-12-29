package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.GrapeCategory;

import java.sql.Timestamp;

public class GrapeCategoryModel implements EntityModel<GrapeCategory> {

    private int id;
    private String category;
    private Timestamp created_at;
    private  Timestamp updated_at;

    public GrapeCategoryModel() {}

    public GrapeCategoryModel(int id, String category, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.category = category;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public GrapeCategoryModel(GrapeCategoryModel category) {
        this.id = category.getId();
        this.category = category.getCategory();
        this.created_at = category.getCreated_at();
        this.updated_at = category.getUpdated_at();
    }

    public GrapeCategoryModel(GrapeCategory category) {
        this.id = category.getId();
        this.category = category.getCategory();
        this.created_at = category.getCreated_at();
        this.updated_at = category.getUpdated_at();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
    public String toString() {
        return String.format("%s", this.category);
    }

    @Override
    public GrapeCategory toEntity() {
        GrapeCategory temp = new GrapeCategory();
        temp.setId(this.id);
        temp.setCategory(this.category);
        temp.setCreated_at(this.created_at);
        temp.setUpdated_at(this.updated_at);
        return temp;
    }
}
