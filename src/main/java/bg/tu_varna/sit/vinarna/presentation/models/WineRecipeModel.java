package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.WineRecipe;

import java.sql.Timestamp;
import java.util.Objects;

public class WineRecipeModel implements EntityModel<WineRecipe> {
    private int id;
    private WineTypeModel wine_type_id;
    private GrapeSortModel grape_sort_id;
    private Double quantity;
    private Timestamp created_at;
    private Timestamp updated_at;

    public WineRecipeModel() {}

    public WineRecipeModel(int id, WineTypeModel wine_type_id, GrapeSortModel grape_sort_id, Double quantity, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.wine_type_id = wine_type_id;
        this.grape_sort_id = grape_sort_id;
        this.quantity = quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public WineRecipeModel(WineRecipeModel wineRecipe) {
        this.id = wineRecipe.getId();
        this.wine_type_id = wineRecipe.getWine_type_id();
        this.grape_sort_id = wineRecipe.getGrape_sort_id();
        this.quantity = wineRecipe.getQuantity();
        this.created_at = wineRecipe.getCreated_at();
        this.updated_at = wineRecipe.getUpdated_at();
    }

    public WineRecipeModel(WineRecipe wineRecipe) {
        this.id = wineRecipe.getId();
        this.wine_type_id = new WineTypeModel(wineRecipe.getWine_type_id());
        this.grape_sort_id = new GrapeSortModel(wineRecipe.getGrape_sort_id());
        this.quantity = wineRecipe.getQuantity();
        this.created_at = wineRecipe.getCreated_at();
        this.updated_at = wineRecipe.getUpdated_at();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WineTypeModel getWine_type_id() {
        return wine_type_id;
    }

    public void setWine_type_id(WineTypeModel wine_type_id) {
        this.wine_type_id = wine_type_id;
    }

    public GrapeSortModel getGrape_sort_id() {
        return grape_sort_id;
    }

    public void setGrape_sort_id(GrapeSortModel grape_sort_id) {
        this.grape_sort_id = grape_sort_id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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
        if (!(o instanceof WineRecipeModel)) return false;
        WineRecipeModel that = (WineRecipeModel) o;
        return id == that.id && wine_type_id.equals(that.wine_type_id) && grape_sort_id.equals(that.grape_sort_id) && quantity.equals(that.quantity) && created_at.equals(that.created_at) && updated_at.equals(that.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wine_type_id, grape_sort_id, quantity, created_at, updated_at);
    }

    @Override
    public String toString() {
        return "WineRecipeModel{" +
                "id=" + id +
                ", wine_type_id=" + wine_type_id +
                ", grape_sort_id=" + grape_sort_id +
                ", quantity=" + quantity +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }

    @Override
    public WineRecipe toEntity() {
        WineRecipe temp = new WineRecipe();

        temp.setId(this.id);
        temp.setWine_type_id(this.wine_type_id.toEntity());
        temp.setGrape_sort_id(this.grape_sort_id.toEntity());
        temp.setQuantity(this.quantity);
        temp.setCreated_at(this.created_at);
        temp.setUpdated_at(this.updated_at);

        return temp;
    }
}
