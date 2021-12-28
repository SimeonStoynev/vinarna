package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.User;

import java.sql.Timestamp;

public class UserModel implements EntityModel<User> {
    private int id;
    private RoleModel role;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Timestamp created_at;
    private Timestamp updated_at;

    public UserModel() {}

    public UserModel(int id, RoleModel role, String username, String password, String firstName, String lastName, String email, String phone, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public UserModel(User user) {
        this.id = user.getId();
        this.role = new RoleModel(user.getRole());
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.created_at = user.getCreated_at();
        this.updated_at = user.getUpdated_at();

    }

    public UserModel(UserModel user) {
        this.id = user.getId();
        this.role = user.getRole();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.created_at = user.getCreated_at();
        this.updated_at = user.getUpdated_at();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return String.format("%s | %s | %s | %s", this.username, this.firstName, this.lastName, this.role.getName());
    }

    @Override
    public User toEntity() {
        User userTemp = new User();
        userTemp.setId(this.id);
        userTemp.setUsername(this.username);
        userTemp.setRole(this.role.toEntity());
        userTemp.setPassword(this.password);
        userTemp.setFirstName(this.firstName);
        userTemp.setLastName(this.lastName);
        userTemp.setEmail(this.email);
        userTemp.setPhone(this.phone);
        userTemp.setCreated_at(this.created_at);
        userTemp.setUpdated_at(this.updated_at);
        return userTemp;
    }
}
