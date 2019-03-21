package nl.han.oose.controller;

import nl.han.oose.dto.ErrorDTO;
import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import nl.han.oose.persistence.SpotitubePersistenceException;
import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.persistence.UserDAO;
import nl.han.oose.util.TokenGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("login")
public class LoginResource {

    private UserDAO userDAO  = new UserDAO();
    private TokenDAO tokenDAO = new TokenDAO();
    private TokenGenerator tokenGenerator = new TokenGenerator();

    public LoginResource() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        UserDTO authenticatedUser = userDAO.getUser(user.getUser(), user.getPassword());

        if (authenticatedUser != null) {
            String token = UUID.randomUUID().toString();

            TokenDTO tokenDTO = new TokenDTO(token, authenticatedUser.getName());
            tokenDAO.saveToken(authenticatedUser.getName(), token);

            return Response.ok(tokenDTO).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorDTO("401", "Login failed for user: " + user.getUser()))
                    .build();
        }
    }


}
