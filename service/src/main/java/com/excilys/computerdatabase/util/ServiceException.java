package com.excilys.computerdatabase.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends RuntimeException {

    private final Logger LOGGER = LoggerFactory.getLogger(ServiceException.class.getName());

    /**
     * .
     */
    public ServiceException() {
        super();
        LOGGER.debug("Computer database Service exception");
    }

    /**
     * .
     *
     * @param message
     *            message
     */
    public ServiceException(String message) {
        super(message);
        LOGGER.debug("Computer database Service exception");
    }

    /**
     * .
     *
     * @param message
     *            message
     * @param cause
     *            cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.debug("Computer database Service exception");
    }

    /**
     * s .
     *
     * @param cause
     *            cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
        LOGGER.debug("Computer database Service exception");
    }

}
