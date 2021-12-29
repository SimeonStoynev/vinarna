package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.GrapeCategory;
import bg.tu_varna.sit.vinarna.data.entities.GrapeSort;

import java.sql.Timestamp;

public class GrapeSortModel implements EntityModel<GrapeCategory> {

    private int id;
    private String name;
    private GrapeCategoryModel category;
    private Timestamp created_at;
    private Timestamp updated_at;

    private Double quantity;

    public GrapeSortModel() {}

    public GrapeSortModel(int id, String name, GrapeCategoryModel category, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public GrapeSortModel(GrapeSortModel sortModel) {
        this.id = sortModel.getId();
        this.name = sortModel.getName();
        this.category = sortModel.getCategory();
        this.created_at = sortModel.getCreated_at();
        this.updated_at = sortModel.getUpdated_at();
    }

    public GrapeSortModel (GrapeSort grapeSort) {
        this.id = grapeSort.getId();
        this.name = grapeSort.getName();
        this.category = new GrapeCategoryModel(grapeSort.getCategory());
        this.created_at = grapeSort.getCreated_at();
        this.updated_at = grapeSort.getUpdated_at();
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

    public GrapeCategoryModel getCategory() {
        return category;
    }

    public void setCategory(GrapeCategoryModel category) {
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
        return String.format("%s | %s", this.name, this.category.getCategory());
    }

    @Override
    public GrapeCategory toEntity() {
        return null;
    }
}
