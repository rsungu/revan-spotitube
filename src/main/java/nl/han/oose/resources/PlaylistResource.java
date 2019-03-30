package nl.han.oose.resources;

import nl.han.oose.dto.PlaylistDTO;
import nl.han.oose.dto.PlaylistsDTO;
import nl.han.oose.dto.TrackDTO;
import nl.han.oose.dto.TracksDTO;
import nl.han.oose.service.AuthenticationService;
import nl.han.oose.service.PlaylistService;
import nl.han.oose.service.PlaylistServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistService playlistService = new PlaylistServiceImpl();

    public PlaylistResource() {
    }

    @Inject
    public PlaylistResource(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        PlaylistsDTO playlistsDTO = playlistService.getAllPlaylists(token);
        return Response.ok(playlistsDTO).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        playlistService.deletePlaylist(token, playlistId);
        PlaylistsDTO playlistsDTO = playlistService.getAllPlaylists(token);
        return Response.ok(playlistsDTO).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlistDTO, @QueryParam("token") String token) {
        playlistService.addPlaylist(playlistDTO, token);
        PlaylistsDTO playlistsDTO = playlistService.getAllPlaylists(token);
        return Response.ok(playlistsDTO).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(PlaylistDTO playlistDTO, @PathParam("id") int id, @QueryParam("token") String token) {
        playlistService.editPlaylist(playlistDTO, token, id);
        PlaylistsDTO playlistsDTO = playlistService.getAllPlaylists(token);
        return Response.ok(playlistsDTO).build();
    }

    @Path("{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksForPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        TracksDTO tracksDTO = playlistService.getAllTracksForPlaylist(token, playlistId);
        return Response.ok(tracksDTO).build();
    }

    @Path("{playlistId}/tracks/{trackId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
        playlistService.deleteTrackFromPlaylist(token, playlistId, trackId);
        TracksDTO tracksDTO = playlistService.getAllTracksForPlaylist(token, playlistId);
        return Response.ok(tracksDTO).build();
    }

    @Path("{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(TrackDTO trackDTO, @PathParam("id") int playlistId, @QueryParam("token") String token) {
        playlistService.addTrackToPlaylist(token, playlistId, trackDTO);
        TracksDTO tracksDTO = playlistService.getAllTracksForPlaylist(token, playlistId);
        return Response.ok(tracksDTO).build();
    }


}
