package nl.han.oose.persistence;

import nl.han.oose.dto.UserDTO;

public interface UserDAO {
    UserDTO getUser(String username, String password);
}