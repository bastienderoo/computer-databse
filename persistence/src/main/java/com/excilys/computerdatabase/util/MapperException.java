package com.excilys.computerdatabase.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by excilys on 10/05/17.
 */
public class MapperException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperException.class.getName());

    public MapperException() {
        super();
        LOGGER.debug("Computer database mapper exception");
    }

    /**
     * .
     *
     * @param message
     *            message
     */
    public MapperException(String message) {
        super(message);
        LOGGER.debug("Computer database mapper exception");
    }

    /**
     * .
     *
     * @param message
     *            message
     * @param cause
     *            cause
     */
    public MapperException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.debug("Computer database mapper exception");
    }

    /**
     * s .
     *
     * @param cause
     *            cause
     */
    public MapperException(Throwable cause) {
        super(cause);
        LOGGER.debug("Computer database mapper exception");
    }
}
