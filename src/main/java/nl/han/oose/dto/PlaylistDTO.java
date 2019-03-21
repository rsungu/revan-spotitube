package nl.han.oose.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDTO {
    private String id;
    private String name;
    private String owner;
    private List<TrackDTO> tracks;

    public PlaylistDTO(String id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = new ArrayList<>();
    }

    public PlaylistDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


}
