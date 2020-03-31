package pl.kernelerror.firebase.command;

public class Validate {
    public static void notNull(Object object, String message) throws ValidationException {
        if (object == null) {
            throw new ValidationException(message);
        }
    }
}