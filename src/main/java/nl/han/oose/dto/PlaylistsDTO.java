package nl.han.oose.dto;

import nl.han.oose.persistence.PlaylistsDAO;

import java.util.ArrayList;
import java.util.List;

public class PlaylistsDTO {
    private List<PlaylistDTO> playlists;
    private int length;

    PlaylistsDAO playlistsDAO;

    public PlaylistsDTO() {
        playlists = new ArrayList<PlaylistDTO>();
        //playlists.add(new PlaylistDTO("1", "HipHop", "Revan"));
        //playlists.add(new PlaylistDTO("2", "Hardcore", "Revan"));
    }


    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
