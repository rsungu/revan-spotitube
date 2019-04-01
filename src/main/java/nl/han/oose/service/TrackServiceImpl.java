package nl.han.oose.service;

import nl.han.oose.dto.TracksDTO;
import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.persistence.TokenDAOImpl;
import nl.han.oose.persistence.TrackDAO;
import nl.han.oose.persistence.TrackDAOImpl;

public class TrackServiceImpl implements TrackService {

    private TokenDAO tokenDAO = new TokenDAOImpl();
    private TrackDAO trackDAO = new TrackDAOImpl();

    @Override
    public TracksDTO getAllTracks(String token, int forPlaylist) {
        if (tokenDAO.checkToken(token)) {
            return trackDAO.getTracksNotInPlaylist(forPlaylist);
        } else {
            throw new SpotitubeTokenException("Wrong token!");
        }
    }
}
