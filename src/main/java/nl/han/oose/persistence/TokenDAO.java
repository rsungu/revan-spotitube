package nl.han.oose.persistence;

public interface TokenDAO {
    String getUsername(String token);

    void saveToken(String token, String username);

    boolean checkToken(String token);
}
