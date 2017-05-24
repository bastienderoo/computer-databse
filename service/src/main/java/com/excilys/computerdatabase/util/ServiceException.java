package com.excilys.computerdatabase.util;

public class ServiceException extends RuntimeException {


    /**
     * .
     */
    public ServiceException() {
        super();
    }

    /**
     * .
     *
     * @param message message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * .
     *
     * @param message message
     * @param cause   cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**s
     * .
     *
     * @param cause cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

}
