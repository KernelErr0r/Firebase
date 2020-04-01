package pl.kernelerror.firebase.command;

import java.util.Collection;

public class Validate {
    public static <T> void correctIndex(T[] collection, int index, String message) throws ValidationException {
        if (index < 0 || index >= collection.length) {
            throw new ValidationException(message);
        }
    }

    public static <T> void correctIndex(Collection<T> collection, int index, String message) throws ValidationException {
        if (index < 0 || index >= collection.size()) {
            throw new ValidationException(message);
        }
    }

    public static void notNull(Object object, String message) throws ValidationException {
        if (object == null) {
            throw new ValidationException(message);
        }
    }
}