package nl.han.oose.persistence;

import nl.han.oose.dto.UserDTO;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class UserDAOImpl implements UserDAO {

    @Override
    public UserDTO getUser(String username, String password) {
        UserDTO foundUser = null;

        try(
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM account WHERE username=? AND password=?")
                ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                foundUser = new UserDTO();
                foundUser.setName(resultSet.getString("name"));
                foundUser.setUser(username);
                foundUser.setPassword(password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foundUser;
    }
}
