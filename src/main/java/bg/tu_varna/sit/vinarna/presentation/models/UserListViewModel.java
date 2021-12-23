package bg.tu_varna.sit.vinarna.presentation.models;

import bg.tu_varna.sit.vinarna.data.entities.Role;

public class UserListViewModel {
    private String username;
    private String firstName;
    private String lastName;
    private Role role;

    public UserListViewModel(String username, String firstName, String lastName, Role role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;

    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s", this.username, this.firstName, this.lastName, this.role.getName());
    }
}
