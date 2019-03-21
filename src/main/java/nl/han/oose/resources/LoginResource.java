package nl.han.oose.resources;

import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import nl.han.oose.service.AuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private AuthenticationService authenticationService;

    public LoginResource() {
    }

    @Inject
    public LoginResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        TokenDTO token = authenticationService.login(user.getUser(), user.getPassword());
        return Response.ok(token).build();
    }


//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response login(UserDTO user) {
//        UserDTO authenticatedUser = userDAO.getUser(user.getUser(), user.getPassword());
//
//        if (authenticatedUser != null) {
//            String token = UUID.randomUUID().toString();
//
//            TokenDTO tokenDTO = new TokenDTO(token, authenticatedUser.getName());
//            tokenDAO.saveToken(authenticatedUser.getName(), token);
//
//            return Response.ok(tokenDTO).build();
//        } else {
//            return Response.status(Response.Status.UNAUTHORIZED)
//                    .entity(new ErrorDTO("401", "Login failed for user: " + user.getUser()))
//                    .build();
//        }
//    }


}
