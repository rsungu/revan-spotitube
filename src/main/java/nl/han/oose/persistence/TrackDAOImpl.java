package nl.han.oose.persistence;

import nl.han.oose.dto.TrackDTO;
import nl.han.oose.dto.TracksDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAOImpl implements TrackDAO {

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO trackDTO) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO playlist_track (playlistId, trackId)" +
                                "VALUES (?,?)")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackDTO.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistId, int trackId) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM playlist_track " +
                                "WHERE playlistId=? " +
                                "AND trackId=?")
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TracksDTO getTracksNotInPlaylist(int playlistId) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM track T LEFT OUTER JOIN (" +
                                    "SELECT * FROM track T1 INNER JOIN playlist_track PT " +
                                        "ON T1.id=PT.trackId " +
                                    "WHERE playlistId=?) AS TP " +
                                "ON T.id=TP.trackId " +
                                "WHERE TP.trackId IS NULL"
                )
        ) {
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return setTracks(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TracksDTO getTracksForPlaylist(int playlistId) {
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM track T INNER JOIN playlist_track PT " +
                                "ON T.id = PT.trackId " +
                            "WHERE playlistId=?")
        ) {
            preparedStatement.setInt(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return setTracks(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private TracksDTO setTracks(ResultSet resultSet) throws SQLException {
        TracksDTO tracksDTO = new TracksDTO();
        List<TrackDTO> tracks = new ArrayList<>();
        while (resultSet.next()) {
            tracks.add(new TrackDTO(resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("performer"),
                    resultSet.getInt("duration"),
                    resultSet.getString("album"),
                    resultSet.getInt("playcount"),
                    resultSet.getString("publicationDate"),
                    resultSet.getString("description"),
                    resultSet.getBoolean("offlineAvailable")
            ));
        }
        tracksDTO.setTracks(tracks);
        return tracksDTO;
    }

}
