package io.github.squarespheres.oslobikeswebservice.webservice;

public class InvalidClientIdException extends Exception {


    public InvalidClientIdException(String message) {
        super(message);
    }

    public InvalidClientIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidClientIdException(Throwable cause) {
        super(cause);
    }
}
