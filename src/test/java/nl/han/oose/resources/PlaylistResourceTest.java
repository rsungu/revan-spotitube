package nl.han.oose.resources;

import nl.han.oose.dto.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistResourceTest {

    private PlaylistResource sut;

    @BeforeEach
    void setUp() {
        sut = new PlaylistResource();
    }

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