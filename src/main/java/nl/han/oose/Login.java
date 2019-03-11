package nl.han.oose.resources;

import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("login")
public class Login {

    private static Map<String, UserDTO> userMap = new HashMap<>();

    public Login() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        return Response.ok(new TokenDTO("12345", "Revan Sungu")).build();
    }


}
