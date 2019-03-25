package nl.han.oose.resources;

import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.persistence.TrackDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    private TokenDAO tokenDAO = new TokenDAO();
    private TrackDAO trackDAO = new TrackDAO();

    public TrackResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("forPlaylist") String forPlaylist) {
        return Response.ok(trackDAO.getTracks(forPlaylist)).build();
    }

}
