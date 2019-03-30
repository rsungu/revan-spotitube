package nl.han.oose.service;

import nl.han.oose.dto.PlaylistDTO;
import nl.han.oose.dto.PlaylistsDTO;
import nl.han.oose.dto.TrackDTO;
import nl.han.oose.dto.TracksDTO;

import javax.sound.midi.Track;

public interface PlaylistService {
    PlaylistsDTO getAllPlaylists(String token);

    void deletePlaylist(String token, int playlistId);

    void addPlaylist(PlaylistDTO playlistDTO, String token);

    void editPlaylist(PlaylistDTO playlistDTO, String token, int id);

    TracksDTO getAllTracksForPlaylist(String token, int id);

    void deleteTrackFromPlaylist(String token, int playlistId, int trackId);

    void addTrackToPlaylist(String token, int playlistId, TrackDTO trackDTO);
}
