package com.excilys.computerdatabase.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputerDatabaseDAOException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDatabaseDAOException.class.getName());

    /**
     * .
     */
    public ComputerDatabaseDAOException() {
        super();
        LOGGER.debug("Computer database DAO exception");
    }

    /**
     * .
     *
     * @param message message
     */
    public ComputerDatabaseDAOException(String message) {
        super(message);
        LOGGER.debug("Computer database DAO exception");
    }

    /**
     * .
     *
     * @param message message
     * @param cause   cause
     */
    public ComputerDatabaseDAOException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.debug("Computer database DAO exception");
    }

    /**
     * .
     *
     * @param cause cause
     */
    public ComputerDatabaseDAOException(Throwable cause) {
        super(cause);
        LOGGER.debug("Computer database DAO exception");
    }

}
