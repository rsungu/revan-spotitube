package nl.han.oose.persistence;

import nl.han.oose.dto.TrackDTO;
import nl.han.oose.dto.TracksDTO;

public interface TrackDAO {
    void addTrackToPlaylist(int playlistId, TrackDTO trackDTO);

    void deleteTrackFromPlaylist(int playlistId, int trackId);

    TracksDTO getTracksNotInPlaylist(int playlistId);

    TracksDTO getTracksForPlaylist(int playlistId);
}
