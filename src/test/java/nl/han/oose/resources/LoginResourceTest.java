package nl.han.oose.resources;

import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import nl.han.oose.service.AuthenticationService;
import nl.han.oose.service.SpotitubeLoginException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class LoginResourceTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private LoginResource sut;

    @Test
    void loginSucces() {
        Mockito.when(authenticationService.login("revan", "password"))
                .thenReturn(new TokenDTO("1234", "Testuser"));

        UserDTO userDTO = new UserDTO("revan", "password");
        Response actualResult = sut.login(userDTO);

        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        TokenDTO tokenDTO = (TokenDTO) actualResult.getEntity();
        assertEquals("Testuser", tokenDTO.getUser());
        assertEquals("1234", tokenDTO.getToken());

    }

    @Test
    void loginFailure() {
        Mockito.when(authenticationService.login(anyString(), anyString()))
                .thenThrow(new SpotitubeLoginException("Login failed for user."));

        SpotitubeLoginException spotitubeLoginException = assertThrows(SpotitubeLoginException.class, () -> sut.login(new UserDTO("revan", "password")));

        assertEquals("Login failed for user.", spotitubeLoginException.getMessage());
    }

}