package nl.han.oose.persistence;

import nl.han.oose.dto.TrackDTO;
import nl.han.oose.dto.TracksDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO {

    public TracksDTO getTracks(String playlistId) {
        TracksDTO tracksDTO = new TracksDTO();
        List<TrackDTO> tracks = new ArrayList<>();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM track")
        ) {
            preparedStatement.setString(1, playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();

//            while (resultSet.next()) {
//                tracks.add(new TrackDTO(resultSet.getInt("trackId"),
//                        resultSet.getString("title"),
//                        resultSet.getString("performer"),
//                        resultSet.getInt("duration"),
//                        resultSet.getString("album"),
//                        resultSet.getInt("playcount"),
//                        resultSet.getString("publicationDate"),
//                        resultSet.getString("description"),
//                        resultSet.getBoolean("offlineAvailable")
//                ));
//            }
            tracksDTO.setTracks(tracks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tracksDTO;
    }

}
