package nl.han.oose.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDAO {

    public void saveToken(String token, String username) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO token (username, token, expiredate) VALUES (?, ?, now() + interval 2 day)")
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, token);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkToken(String token) {
        boolean foundToken = false;
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM token WHERE token=? AND expiredate >= now()")
        ) {
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (resultSet.getString("token").equals(token)) {
                    foundToken = true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foundToken;
    }
}
