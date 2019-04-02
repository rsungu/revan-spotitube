package nl.han.oose.resources;

import nl.han.oose.dto.*;

import nl.han.oose.persistence.PlaylistsDAO;
import nl.han.oose.persistence.UserDAO;
import nl.han.oose.service.PlaylistService;
import nl.han.oose.service.SpotitubeLoginException;
import nl.han.oose.service.SpotitubeTokenException;
import nl.han.oose.util.TokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class PlaylistResourceTest {

    private final int PLAYLIST_ID = 1;
    private final int TRACK_ID = 2;
    private final String TOKEN = "TOKEN";

    @Mock
    private PlaylistService playlistService;


    @InjectMocks
    private PlaylistResource sut;

    @Test
    public void getAllPlaylistsCorrectToken() {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();

        Mockito.when(playlistService.getAllPlaylists(TOKEN))
                .thenReturn(playlistsDTO);

        Response actualResult = sut.getAllPlaylists(TOKEN);

        //Vergelijk status
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        Mockito.verify(playlistService).getAllPlaylists(TOKEN);
        assertEquals(playlistsDTO, actualResult.getEntity());
    }

    @Test
    public void getAllPlaylistsFalseToken() {
        Mockito.when(playlistService.getAllPlaylists(anyString()))
                .thenThrow(new SpotitubeTokenException("Wrong token!"));

        SpotitubeTokenException spotitubeTokenException = assertThrows(SpotitubeTokenException.class, () -> sut.getAllPlaylists("12345"));

        assertEquals("Wrong token!", spotitubeTokenException.getMessage());
    }

    @Test
    public void deletePlaylistRoeptServiceAan() {
        sut.deletePlaylist(PLAYLIST_ID, TOKEN);
        Mockito.verify(playlistService).deletePlaylist(TOKEN, PLAYLIST_ID);
    }

    @Test
    public void addPlaylistRoeptServiceAan() {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        sut.addPlaylist(playlistDTO, TOKEN);
        Mockito.verify(playlistService).addPlaylist(playlistDTO, TOKEN);
    }

    @Test
    public void editPlaylistRoeptServiceAan() {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        sut.editPlaylist(playlistDTO, PLAYLIST_ID, TOKEN);
        Mockito.verify(playlistService).editPlaylist(playlistDTO, TOKEN, PLAYLIST_ID);
    }

    @Test
    public void getAllTracksForPlaylistRoeptServiceAan() {
        TracksDTO tracksDTO = new TracksDTO();

        Mockito.when(playlistService.getAllTracksForPlaylist(anyString(), anyInt()))
                .thenReturn(tracksDTO);

        Response actualResult = sut.getAllTracksForPlaylist(PLAYLIST_ID, TOKEN);

        Mockito.verify(playlistService).getAllTracksForPlaylist(TOKEN, PLAYLIST_ID);
        assertEquals(tracksDTO, actualResult.getEntity());
    }

    @Test
    public void deleteTrackFromPlaylistRoeptServiceAan() {
        sut.deleteTrackFromPlaylist(PLAYLIST_ID, TRACK_ID, TOKEN);
        Mockito.verify(playlistService).deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);
    }

    @Test
    public void addTrackToPlaylistRoeptServiceAan() {
        TrackDTO trackDTO = new TrackDTO();
        sut.addTrackToPlaylist(trackDTO, PLAYLIST_ID, TOKEN);
        Mockito.verify(playlistService).addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);
    }

}