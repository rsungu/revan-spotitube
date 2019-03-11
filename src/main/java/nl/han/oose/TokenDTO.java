package nl.han.oose.dto;

public class TokenDTO {
    private String token;
    private String user;

    public TokenDTO(String token, String user) {
        this.token = token;
        this.user = user;
    }
}
