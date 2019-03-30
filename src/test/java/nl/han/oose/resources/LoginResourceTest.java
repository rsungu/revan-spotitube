package nl.han.oose.resources;

import nl.han.oose.dto.ErrorDTO;
import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import nl.han.oose.persistence.UserDAO;
import nl.han.oose.util.TokenGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LoginResourceTest {

    @Mock
    private UserDAO userDAOStub;

    @Mock
    private TokenGenerator tokenGenerator;

    @InjectMocks
    private LoginResource sut;

    @Test
    void loginSucces() {
        Mockito.when(tokenGenerator.generateToken()).thenReturn("12345");

        UserDTO mockedUser = new UserDTO();
        mockedUser.setName("Revan Sungu");
        mockedUser.setPassword("testpassword");
        mockedUser.setUser("testuser");
        Mockito.when(userDAOStub.getUser("testuser", "testpassword"))
                .thenReturn(mockedUser);

        UserDTO userDTO = new UserDTO("testuser", "testpassword");
        Response actualResult = sut.login(userDTO);
        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());

        TokenDTO tokenDTO = (TokenDTO) actualResult.getEntity();
        assertEquals("Revan Sungu", tokenDTO.getUser());
        assertEquals("12345", tokenDTO.getToken());

    }

    @Test
    void loginFailure() {
        UserDTO userDTO = new UserDTO("revan", "12345");
        Response actualResult = sut.login(userDTO);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());

        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
        assertEquals("Login failed for user: " + userDTO.getUser(), errorDTO.getMessage());

    }

}