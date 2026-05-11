package gsm.exception;

public class MSDejaAttacheException extends Exception {

    public MSDejaAttacheException(String message) {
        super(message);
    }

    public MSDejaAttacheException() {
        super("MS deja attachee a une BTS.");
    }
}