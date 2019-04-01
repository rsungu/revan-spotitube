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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class PlaylistResourceTest {

    private final int PLAYLIST_ID = 1;
    private final String TOKEN = "TOKEN";

    @Mock
    private PlaylistService playlistService;


    @InjectMocks
    private PlaylistResource sut;

    @Test
    public void getAllPlaylistsCorrectToken() {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();

        Mockito.when(playlistService.getAllPlaylists(TOKEN)).thenReturn(playlistsDTO);

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
    public void deletePlaylist() {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();

        Mockito.when(playlistService.deletePlaylist(TOKEN, PLAYLIST_ID)).thenReturn(playlistsDTO);

        Response actualResult = sut.getAllPlaylists(TOKEN);

        //Vergelijk status
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        Mockito.verify(playlistService).getAllPlaylists(TOKEN);
        assertEquals(playlistsDTO, actualResult.getEntity());
    }

}