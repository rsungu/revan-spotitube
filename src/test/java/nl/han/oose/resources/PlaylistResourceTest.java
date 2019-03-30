package nl.han.oose.resources;

import nl.han.oose.dto.*;

import nl.han.oose.persistence.UserDAO;
import nl.han.oose.util.TokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PlaylistResourceTest {

    @Mock
    private UserDAO userDAOStub;

    @Mock
    private TokenGenerator tokenGenerator;

    @InjectMocks
    private PlaylistResource sut;

    @Test
    public void getAllPlaylistsCorrectToken() {
        String token = "1234";
        Response actualResult = sut.getAllPlaylists(token);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        PlaylistsDTO playlistsDTO = (PlaylistsDTO) actualResult.getEntity();
        assertEquals(0, playlistsDTO.getLength());
    }

    @Test
    public void getAllPlaylistsFalseToken() {
        String token = "12345";
        System.out.println(token);
        Response actualResult = sut.getAllPlaylists(token);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());

        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("401", errorDTO.getCode());
        assertEquals("Wrong token", errorDTO.getMessage());
    }


    @Test
    public void deletePlaylist() {
    }
}