package aliaksandrkryvapust.atm.exeption;

public class UserNotExistException extends Exception {
    public UserNotExistException(String message) {
        super(message);
    }
}
