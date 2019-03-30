package nl.han.oose.service;

import nl.han.oose.dto.PlaylistDTO;
import nl.han.oose.dto.PlaylistsDTO;
import nl.han.oose.dto.TrackDTO;
import nl.han.oose.dto.TracksDTO;
import nl.han.oose.persistence.*;

public class PlaylistServiceImpl implements PlaylistService {

    private TokenDAO tokenDAO = new TokenDAOImpl();
    private PlaylistsDAOImpl playlistsDAO = new PlaylistsDAOImpl();
    private TrackDAO trackDAO = new TrackDAOImpl();

    @Override
    public PlaylistsDTO getAllPlaylists(String token) {
        if (tokenDAO.checkToken(token)) {
            return playlistsDAO.getAllPlaylists(tokenDAO.getUsername(token));
        } else {
            throw new SpotitubeTokenException("Wrong token!");
        }
    }

    @Override
    public void deletePlaylist(String token, int playlistId) {
        if (tokenDAO.checkToken(token)) {
            playlistsDAO.deletePlaylist(playlistId);
        } else {
            throw new SpotitubeTokenException("Wrong token!");
        }
    }

    @Override
    public void addPlaylist(PlaylistDTO playlistDTO, String token) {
        if (tokenDAO.checkToken(token)) {
            playlistsDAO.addPlaylist(playlistDTO);
        } else {
            throw new SpotitubeTokenException("Wrong token!");
        }
    }

    @Override
    public void editPlaylist(PlaylistDTO playlistDTO, String token, int id) {
        if (tokenDAO.checkToken(token)) {
            playlistsDAO.editPlaylist(playlistDTO.getName(), id);
        } else {
            throw new SpotitubeTokenException("Wrong token!");
        }
    }

    @Override
    public TracksDTO getAllTracksForPlaylist(String token, int id) {
        if (tokenDAO.checkToken(token)) {
            return trackDAO.getTracksForPlaylist(id);
        } else {
            throw new SpotitubeTokenException("Wrong token!");
        }
    }

    @Override
    public void deleteTrackFromPlaylist(String token, int playlistId, int trackId) {
        if (tokenDAO.checkToken(token)) {
            trackDAO.deleteTrackFromPlaylist(playlistId, trackId);
        } else {
            throw new SpotitubeTokenException("Wrong token!");
        }
    }

    @Override
    public void addTrackToPlaylist(String token, int playlistId, TrackDTO trackDTO) {
        if (tokenDAO.checkToken(token)) {
            trackDAO.addTrackToPlaylist(playlistId, trackDTO);
        } else {
            throw new SpotitubeTokenException("Wrong token!");
        }
    }

}
