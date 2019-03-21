package nl.han.oose.dto;

public class UserDTO {

    private String name;
    private String user;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
