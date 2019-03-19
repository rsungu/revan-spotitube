package nl.han.oose;

import nl.han.oose.controller.LoginController;
import nl.han.oose.dto.ErrorDTO;
import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;


public class LoginControllerTest {

    private LoginController sut;

    @BeforeEach
    void setUp() {
        sut = new LoginController();
    }

    @Test
    void loginSucces() {
        UserDTO userDTO = new UserDTO("revan", "1234");
        Response actualResult = sut.login(userDTO);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        TokenDTO tokenDTO = (TokenDTO) actualResult.getEntity();
        assertEquals("12345", tokenDTO.getToken());
        assertEquals("Revan Sungu", tokenDTO.getUser());
    }

    @Test
    void loginFailure() {
        UserDTO userDTO = new UserDTO("revan", "12345");
        Response actualResult = sut.login(userDTO);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());

        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("Login failed for user: " + userDTO.getUsername(), errorDTO.getMessage());

    }

}