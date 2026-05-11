package gsm.exception;

public class BTSIntrouvableException extends Exception {

    public BTSIntrouvableException(String message) {
        super(message);
    }

    public BTSIntrouvableException() {
        super("BTS introuvable dans le reseau.");
    }
}