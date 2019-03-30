package nl.han.oose.resources;

import nl.han.oose.dto.TracksDTO;
import nl.han.oose.service.TrackService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    private TrackService trackService = new TrackService();

    public TrackResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracks(@QueryParam("forPlaylist") int forPlaylist, @QueryParam("token") String token) {
        TracksDTO tracksDTO = trackService.getAllTracks(token, forPlaylist);
        return Response.ok(tracksDTO).build();
    }
}
