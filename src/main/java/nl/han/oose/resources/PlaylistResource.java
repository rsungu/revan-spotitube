package nl.han.oose.resources;

import nl.han.oose.dto.*;
import nl.han.oose.persistence.PlaylistsDAO;
import nl.han.oose.persistence.TokenDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private TokenDAO tokenDAO = new TokenDAO();
    private PlaylistsDAO playlistsDAO = new PlaylistsDAO();

    public PlaylistResource() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        if (tokenDAO.checkToken(token)) {
            return Response.ok(playlistsDAO.getAllPlaylists("revan")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO("401", "Wrong token"))
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        if (tokenDAO.checkToken(token)) {
            playlistsDAO.deletePlaylist(id);
            return Response.ok(playlistsDAO.getAllPlaylists("revan")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO("401", "Wrong token"))
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlistDTO, @QueryParam("token") String token) {
        if (tokenDAO.checkToken(token)) {
            playlistsDAO.addPlaylist(playlistDTO);
            return Response.ok(playlistsDAO.getAllPlaylists("revan")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO("401", "Wrong token"))
                    .build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(PlaylistDTO playlistDTO, @PathParam("id") String id, @QueryParam("token") String token) {
        if (tokenDAO.checkToken(token)) {
            playlistsDAO.editPlaylist(playlistDTO.getName(), Integer.parseInt(id));
            return Response.ok(playlistsDAO.getAllPlaylists("revan")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO("401", "Wrong token"))
                    .build();
        }
    }


}
