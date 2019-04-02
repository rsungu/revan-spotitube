package nl.han.oose.service;


import nl.han.oose.dto.TokenDTO;
import nl.han.oose.dto.UserDTO;
import nl.han.oose.persistence.TokenDAO;
import nl.han.oose.persistence.TokenDAOImpl;
import nl.han.oose.persistence.UserDAO;
import nl.han.oose.persistence.UserDAOImpl;
import nl.han.oose.util.TokenGenerator;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserDAO userDAO = new UserDAOImpl();
    private TokenDAO tokenDAO = new TokenDAOImpl();
    private TokenGenerator tokenGenerator = new TokenGenerator();

    @Inject
    public AuthenticationServiceImpl(UserDAO userDAO, TokenDAO tokenDAO, TokenGenerator tokenGenerator) {
        this.userDAO = userDAO;
        this.tokenDAO = tokenDAO;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public TokenDTO login(String username, String password) {
        UserDTO user = userDAO.getUser(username, password);
        if (user != null) {
            TokenDTO tokenDTO = new TokenDTO(tokenGenerator.generateToken(), user.getUser());
            tokenDAO.saveToken(tokenDTO.getToken(), tokenDTO.getUser());
            return tokenDTO;
        } else {
            throw new SpotitubeLoginException("Login failed for user " + username);
        }
    }
}


