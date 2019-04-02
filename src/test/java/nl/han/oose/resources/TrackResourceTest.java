package nl.han.oose.resources;

import nl.han.oose.dto.PlaylistDTO;
import nl.han.oose.dto.TracksDTO;
import nl.han.oose.service.PlaylistService;
import nl.han.oose.service.TrackService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import java.awt.geom.RectangularShape;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class TrackResourceTest {

    private final int PLAYLIST_ID = 1;
    private final String TOKEN = "TOKEN";

    @Mock
    private TrackService trackService;

    @InjectMocks
    private TrackResource sut;

    @Test
    void getAllTracksRoeptServiceAan() {
        TracksDTO tracksDTO = new TracksDTO();

        Mockito.when(trackService.getAllTracks(anyString(), anyInt())).thenReturn(tracksDTO);

        Response actualResult = sut.getAllTracks(PLAYLIST_ID, TOKEN);

        Mockito.verify(trackService).getAllTracks(TOKEN, PLAYLIST_ID);
        assertEquals(tracksDTO, actualResult.getEntity());
    }
}