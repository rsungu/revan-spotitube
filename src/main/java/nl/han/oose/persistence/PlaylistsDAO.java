package nl.han.oose.persistence;

import nl.han.oose.dto.PlaylistDTO;
import nl.han.oose.dto.PlaylistsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsDAO {

    public PlaylistsDTO getAllPlaylists(String username) {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        List<PlaylistDTO> playlists = new ArrayList<PlaylistDTO>();

        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM playlist INNER JOIN playlist_owner " +
                                "ON playlist.playlistId = playlist_owner.playlistId " +
                                "WHERE playlist_owner.username=?")
        ) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                playlists.add(new PlaylistDTO(
                        resultSet.getString("playlistId"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("owner")
                ));
            }
            playlistsDTO.setPlaylists(playlists);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistsDTO;
    }

    public void deletePlaylist(String playlistId) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM playlist WHERE playlistId=?")
        ) {
            preparedStatement.setString(1, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM playlist_owner WHERE playlistId=?")
        ) {
            preparedStatement.setString(1, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlaylist(PlaylistDTO playlistDTO) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO playlist (playlistId, name, owner) VALUES (?,?,?)")
        ) {
            preparedStatement.setString(1, playlistDTO.getId());
            preparedStatement.setString(2, playlistDTO.getName());
            preparedStatement.setBoolean(3, playlistDTO.getOwner());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }



}
