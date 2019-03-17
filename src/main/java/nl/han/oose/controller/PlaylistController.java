package nl.han.oose;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlist")
public class PlaylistController {

    public PlaylistController() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("token") TokenDTO token) {
        return Response.ok().build();
    }
}
