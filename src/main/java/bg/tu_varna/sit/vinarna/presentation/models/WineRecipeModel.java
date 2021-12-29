package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.WineRecipe;

import java.sql.Timestamp;

public class WineRecipeModel implements EntityModel<WineRecipe> {
    private int id;
    private Long wine_type_id;
    private Long grape_sort_id;
    private Long quantity;
    private Timestamp created_at;
    private Timestamp updated_at;

    public WineRecipeModel() {}

    public WineRecipeModel(int id, Long wine_type_id, Long grape_sort_id, Long quantity, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.wine_type_id = wine_type_id;
        this.grape_sort_id = grape_sort_id;
        this.quantity = quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getWine_type_id() {
        return wine_type_id;
    }

    public void setWine_type_id(Long wine_type_id) {
        this.wine_type_id = wine_type_id;
    }

    public Long getGrape_sort_id() {
        return grape_sort_id;
    }

    public void setGrape_sort_id(Long grape_sort_id) {
        this.grape_sort_id = grape_sort_id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
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
        temp.setWine_type_id(this.wine_type_id);
        temp.setGrape_sort_id(this.grape_sort_id);
        temp.setQuantity(this.quantity);
        temp.setCreated_at(this.created_at);
        temp.setUpdated_at(this.updated_at);

        return temp;
    }
}
