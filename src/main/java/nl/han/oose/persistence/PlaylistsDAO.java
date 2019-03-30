package nl.han.oose.persistence;

import nl.han.oose.dto.PlaylistDTO;
import nl.han.oose.dto.PlaylistsDTO;

public interface PlaylistsDAO {

    PlaylistsDTO getAllPlaylists(String username);

    void editPlaylist(String name, int playlistId);

    void deletePlaylist(int playlistId);

    void addPlaylist(PlaylistDTO playlistDTO);

}
