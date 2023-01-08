package aliaksandrkryvapust.atm.exeption;

public class LimitExceededException extends Exception {
    public LimitExceededException(String message) {
        super(message);
    }
}
