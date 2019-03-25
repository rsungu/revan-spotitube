package nl.han.oose.dto;

import java.util.ArrayList;
import java.util.List;

public class TracksDTO {
    private List<TrackDTO> tracks;

    public TracksDTO() {
        tracks = new ArrayList<>();
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
