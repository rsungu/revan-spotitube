package nl.han.oose.service;

import nl.han.oose.dto.TracksDTO;

public interface TrackService {
    TracksDTO getAllTracks(String token, int forPlaylist);
}
