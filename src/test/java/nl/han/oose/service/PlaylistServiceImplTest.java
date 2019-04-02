package nl.han.oose.service;

import nl.han.oose.dto.*;
import nl.han.oose.persistence.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class PlaylistServiceImplTest {

    private final int PLAYLIST_ID = 1;
    private final int TRACK_ID = 2;
    private final String TOKEN = "TOKEN";

    @Mock
    private TrackDAO trackDAO;

    @Mock
    private TokenDAO tokenDAO;

    @Mock
    private PlaylistsDAO playlistsDAO;

    @InjectMocks
    private PlaylistServiceImpl sut;

    @Test
    void getAllPlaylistsCorrectToken() {
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();

        Mockito.when(tokenDAO.checkToken(TOKEN))
                .thenReturn(true);
        Mockito.when(playlistsDAO.getAllPlaylists(anyString()))
                .thenReturn(playlistsDTO);
        Mockito.when(tokenDAO.getUsername(anyString()))
                .thenReturn("User");

        PlaylistsDTO actualResult = sut.getAllPlaylists(TOKEN);
        Mockito.verify(playlistsDAO).getAllPlaylists("User");
        assertEquals(playlistsDTO, actualResult);
    }

    @Test
    void getAllPlaylistsTokenControleFailure() {
        SpotitubeTokenException spotitubeTokenException = new SpotitubeTokenException("Wrong token!");

        Mockito.when(tokenDAO.checkToken(TOKEN))
                .thenReturn(false);

        SpotitubeTokenException actualResult = assertThrows(SpotitubeTokenException.class, () -> sut.getAllPlaylists(TOKEN));

        assertEquals(spotitubeTokenException.getMessage(), actualResult.getMessage());
    }

    @Test
    void deletePlaylist() {
        Mockito.when(tokenDAO.checkToken(TOKEN))
                .thenReturn(true);

        sut.deletePlaylist(TOKEN, PLAYLIST_ID);
        Mockito.verify(playlistsDAO).deletePlaylist(PLAYLIST_ID);
    }

    @Test
    void addPlaylist() {
        PlaylistDTO playlistDTO = new PlaylistDTO();

        Mockito.when(tokenDAO.checkToken(TOKEN))
                .thenReturn(true);

        sut.addPlaylist(playlistDTO, TOKEN);
        Mockito.verify(playlistsDAO).addPlaylist(playlistDTO);
    }

    @Test
    void editPlaylist() {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("Playlist");

        Mockito.when(tokenDAO.checkToken(TOKEN))
                .thenReturn(true);
        sut.editPlaylist(playlistDTO, TOKEN, PLAYLIST_ID);
        Mockito.verify(playlistsDAO).editPlaylist(playlistDTO.getName(), PLAYLIST_ID);
    }

    @Test
    void getAllTracksForPlaylist() {
        TracksDTO tracksDTO = new TracksDTO();

        Mockito.when(tokenDAO.checkToken(TOKEN))
                .thenReturn(true);
        Mockito.when(trackDAO.getTracksForPlaylist(PLAYLIST_ID))
                .thenReturn(tracksDTO);

        TracksDTO actualResult = sut.getAllTracksForPlaylist(TOKEN, PLAYLIST_ID);
        Mockito.verify(trackDAO).getTracksForPlaylist(PLAYLIST_ID);
        assertEquals(tracksDTO, actualResult);
    }

    @Test
    void deleteTrackFromPlaylist() {
        Mockito.when(tokenDAO.checkToken(TOKEN))
                .thenReturn(true);

        sut.deleteTrackFromPlaylist(TOKEN, PLAYLIST_ID, TRACK_ID);
        Mockito.verify(trackDAO).deleteTrackFromPlaylist(PLAYLIST_ID, TRACK_ID);
    }

    @Test
    void addTrackToPlaylist() {
        TrackDTO trackDTO = new TrackDTO();

        Mockito.when(tokenDAO.checkToken(TOKEN))
                .thenReturn(true);

        sut.addTrackToPlaylist(TOKEN, PLAYLIST_ID, trackDTO);
        Mockito.verify(trackDAO).addTrackToPlaylist(PLAYLIST_ID, trackDTO);
    }
}