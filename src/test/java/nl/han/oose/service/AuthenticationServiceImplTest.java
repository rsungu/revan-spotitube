package nl.han.oose.service;

import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.persistence.UserDAO;
import nl.han.oose.util.TokenGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private TokenGenerator tokenGenerator;

    @Mock
    private TokenDAO tokenDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private AuthenticationServiceImpl sut;

    @Test
    void loginSucces() {
        UserDTO userDTO = new UserDTO("Test", "password");

        Mockito.when(userDAO.getUser(anyString(), anyString()))
                .thenReturn(userDTO);
        Mockito.when(tokenGenerator.generateToken()).thenReturn("TOKEN");
        TokenDTO tokenDTO = new TokenDTO("TOKEN", userDTO.getUser());

        sut.login("Test", "password");

        Mockito.verify(tokenDAO).saveToken(tokenDTO.getToken(), tokenDTO.getUser());
    }

    @Test
    void loginCorrectToken() {
    }
}