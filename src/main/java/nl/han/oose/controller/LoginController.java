package nl.han.oose;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {

    public LoginController() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        //controleren op null waardes
        if (user.getUser().equals("revan") && user.getPassword().equals("1234")) {
            return Response.ok(new TokenDTO("12345", "Revan Sungu")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO("401", "Login failed for user: " + user.getUser()))
                    .build();
        }
    }


}
