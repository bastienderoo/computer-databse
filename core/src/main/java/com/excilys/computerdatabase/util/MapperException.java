package com.excilys.computerdatabase.util;

/**
 * Created by excilys on 10/05/17.
 */
public class MapperException extends RuntimeException {
    /**
     * .
     */
    public MapperException() {
        super();
    }

    /**
     * .
     *
     * @param message
     *            message
     */
    public MapperException(String message) {
        super(message);
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
    }

    /**
     * s .
     *
     * @param cause
     *            cause
     */
    public MapperException(Throwable cause) {
        super(cause);
    }
}
