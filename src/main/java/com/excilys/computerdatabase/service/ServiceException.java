package com.excilys.computerdatabase.service;

public class ServiceException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * .
     */
        public ServiceException() {
            super();
        }
    /**
     * .
     * @param message message
     */
        public ServiceException(String message) {
            super(message);
        }
    /**
     * .
     * @param message message
     * @param cause cause
     */
        public ServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    /**
     * .
     * @param cause cause
     */
        public ServiceException(Throwable cause) {
            super(cause);
        }

}
