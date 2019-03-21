package nl.han.oose.persistence;

import java.sql.SQLException;

public class SpotitubePersistenceException extends RuntimeException {
    public SpotitubePersistenceException(Throwable e) {
        super(e);
    }
}
