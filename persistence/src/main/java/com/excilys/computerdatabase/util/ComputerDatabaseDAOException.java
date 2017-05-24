package com.excilys.computerdatabase.util;

public class ComputerDatabaseDAOException extends RuntimeException {



    /**
     * .
     */
    public ComputerDatabaseDAOException() {
        super();
    }

    /**
     * .
     *
     * @param message message
     */
    public ComputerDatabaseDAOException(String message) {
        super(message);
    }

    /**
     * .
     *
     * @param message message
     * @param cause   cause
     */
    public ComputerDatabaseDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * .
     *
     * @param cause cause
     */
    public ComputerDatabaseDAOException(Throwable cause) {
        super(cause);
    }

}
