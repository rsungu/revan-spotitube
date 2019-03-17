package nl.han.oose;

import java.util.List;

public class PlaylistsDTO {
    private List<PlaylistsDTO> playlists;
    private int length;

    public PlaylistsDTO() {

    }

    public List<PlaylistsDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistsDTO> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
