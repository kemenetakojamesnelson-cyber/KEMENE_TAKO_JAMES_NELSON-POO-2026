package gsm.exception;

public class MSIntrouvableException extends Exception {

    public MSIntrouvableException(String message) {
        super(message);
    }

    public MSIntrouvableException() {
        super("MS introuvable.");
    }
}