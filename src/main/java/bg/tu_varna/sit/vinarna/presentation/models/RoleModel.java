package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.Role;

import java.sql.Timestamp;

public class RoleModel implements EntityModel<Role> {
    private int id;
    private String name;
    private Timestamp created_at;
    private Timestamp updated_at;

    public RoleModel(){}

    public RoleModel(int id, String name, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public RoleModel(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.created_at = role.getCreated_at();
        this.updated_at = role.getUpdated_at();
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
    public Role toEntity() {
        Role temp = new Role();
        temp.setId(this.id);
        temp.setName(this.name);
        temp.setCreated_at(this.created_at);
        temp.setUpdated_at(this.updated_at);
        return temp;
    }

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }
}
