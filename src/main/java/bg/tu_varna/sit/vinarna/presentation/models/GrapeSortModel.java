package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.GrapeSort;

import java.sql.Timestamp;

public class GrapeSortModel implements EntityModel<GrapeSort> {

    private int id;
    private String name;
    private GrapeCategoryModel category;
    Double wine_liters;
    private Timestamp created_at;
    private Timestamp updated_at;

    public GrapeSortModel() {}

    public GrapeSortModel(int id, String name, GrapeCategoryModel category, Double wine_liters, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.wine_liters = wine_liters;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public GrapeSortModel(GrapeSort grapeSort) {
        this.id = grapeSort.getId();
        this.name = grapeSort.getName();
        this.category = new GrapeCategoryModel(grapeSort.getCategory());
        this.wine_liters = grapeSort.getWine_liters();
        this.created_at = grapeSort.getCreated_at();
        this.updated_at = grapeSort.getUpdated_at();
    }

    public GrapeSortModel(GrapeSortModel sortModel) {
        this.id = sortModel.getId();
        this.name = sortModel.getName();
        this.category = sortModel.getCategory();
        this.wine_liters = sortModel.getWine_liters();
        this.created_at = sortModel.getCreated_at();
        this.updated_at = sortModel.getUpdated_at();
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

    public Double getWine_liters() {
        return wine_liters;
    }

    public void setWine_liters(Double wine_liters) {
        this.wine_liters = wine_liters;
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
    public GrapeSort toEntity() {
        GrapeSort temp = new GrapeSort();

        temp.setId(this.id);
        temp.setName(this.name);
        temp.setCategory(this.category.toEntity());
        temp.setWine_liters(this.wine_liters);
        temp.setCreated_at(this.created_at);
        temp.setUpdated_at(this.updated_at);

        return temp;
    }
}
