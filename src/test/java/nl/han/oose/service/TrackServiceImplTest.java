package nl.han.oose.service;

import nl.han.oose.dto.TracksDTO;
import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.persistence.TrackDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TrackServiceImplTest {

    private final int PLAYLIST_ID = 1;
    private final String TOKEN = "TOKEN";

    @Mock
    TokenDAO tokenDAO;

    @Mock
    TrackDAO trackDAO;

    @InjectMocks
    TrackServiceImpl sut;

    @Test
    void getAllTracks() {
        TracksDTO tracksDTO = new TracksDTO();

        Mockito.when(tokenDAO.checkToken(TOKEN))
                .thenReturn(true);
        Mockito.when(trackDAO.getTracksNotInPlaylist(PLAYLIST_ID))
                .thenReturn(tracksDTO);

        TracksDTO actualResult = sut.getAllTracks(TOKEN, PLAYLIST_ID);

        assertEquals(tracksDTO, actualResult);
    }
}