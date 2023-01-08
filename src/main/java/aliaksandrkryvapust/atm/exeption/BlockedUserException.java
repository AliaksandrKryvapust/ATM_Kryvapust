package aliaksandrkryvapust.atm.exeption;

public class BlockedUserException extends Exception {
    public BlockedUserException(String message) {
        super(message);
    }
}
