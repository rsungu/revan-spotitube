package nl.han.oose.service;

import nl.han.oose.dto.TracksDTO;
import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.persistence.TokenDAOImpl;
import nl.han.oose.persistence.TrackDAO;
import nl.han.oose.persistence.TrackDAOImpl;

import javax.enterprise.inject.Default;
import javax.inject.Inject;


@Default
public class TrackServiceImpl implements TrackService {

    private TokenDAO tokenDAO;
    private TrackDAO trackDAO;

    public TrackServiceImpl() {
    }

    @Inject
    public TrackServiceImpl(TokenDAO tokenDAO, TrackDAO trackDAO) {
        this.tokenDAO = tokenDAO;
        this.trackDAO = trackDAO;
    }

    @Inject


    @Override
    public TracksDTO getAllTracks(String token, int forPlaylist) {
        if (tokenDAO.checkToken(token)) {
            return trackDAO.getTracksNotInPlaylist(forPlaylist);
        } else {
            throw new SpotitubeTokenException("Wrong token!");
        }
    }
}
