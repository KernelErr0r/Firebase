package pl.kernelerror.firebase.command;

public class CommandException extends RuntimeException {
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}