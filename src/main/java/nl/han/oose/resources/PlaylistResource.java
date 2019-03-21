package nl.han.oose.controller;

import nl.han.oose.dto.*;
import nl.han.oose.persistence.TokenDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlists")
public class PlaylistResource {

    private TokenDAO tokenDAO = new TokenDAO();

    public PlaylistResource() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") String token) {
        if (tokenDAO.checkToken(token)) {
            return Response.ok(new PlaylistsDTO()).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO("401", "Wrong token"))
                    .build();
        }
    }

    @DELETE
    @Path(":{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") String id, @QueryParam("token") String token) {
        return Response.ok(id).build();
    }

}
