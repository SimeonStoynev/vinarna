package bg.tu_varna.sit.vinarna.presentation.models;

public class UserListViewModel {
    private String username;
    private String firstName;
    private String lastName;

    public UserListViewModel(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s", this.username, this.firstName, this.lastName);
    }
}
