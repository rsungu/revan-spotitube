package nl.han.oose;

import javax.xml.transform.Result;
import java.sql.*;

public class Main  {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spotitube?serverTimezone=UTC",
                "root",
                "");

                         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
                         ResultSet resultSet = preparedStatement.executeQuery();
             ) {


            while (resultSet.next()) {
                System.out.println(
                        resultSet.getString("username") + ": " +
                        resultSet.getString("password")
                );
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
