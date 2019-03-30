package nl.han.oose.persistence;

import nl.han.oose.dto.PlaylistDTO;
import nl.han.oose.dto.PlaylistsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsDAOImpl implements PlaylistsDAO {

    @Override
    public PlaylistsDTO getAllPlaylists(String username) {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        List<PlaylistDTO> playlists = new ArrayList<>();

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
                        resultSet.getInt("playlistId"),
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

    @Override
    public void editPlaylist(String name, int playlistId) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE playlist SET name=? WHERE playlistId=?")
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePlaylist(int playlistId) {
        deletePlaylistOwner(playlistId);
        deletePlaylistTracks(playlistId);
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM playlist WHERE playlistId=?")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPlaylist(PlaylistDTO playlistDTO) {
        playlistDTO.setId(getId());
        insertPlaylist(playlistDTO);
        insertOwner(playlistDTO, "revan");
    }


    private void deletePlaylistTracks(int playlistId) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM playlist_track WHERE playlistId=?")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deletePlaylistOwner(int playlistId) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM playlist_owner WHERE playlistId=?")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertPlaylist(PlaylistDTO playlistDTO) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO playlist (playlistId, name, owner) VALUES (?,?,?)")
        ) {
            preparedStatement.setInt(1, playlistDTO.getId());
            preparedStatement.setString(2, playlistDTO.getName());
            preparedStatement.setBoolean(3, true);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertOwner(PlaylistDTO playlistDTO, String playlistOwner) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO playlist_owner (playlistId, username) VALUES (?,?)")
        ) {
            preparedStatement.setInt(1, playlistDTO.getId());
            preparedStatement.setString(2, playlistOwner);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getId() {
        int playlistId = 0;
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM playlist ORDER BY playlistId DESC")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                playlistId = resultSet.getInt("playlistId");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistId + 1;
    }

}
