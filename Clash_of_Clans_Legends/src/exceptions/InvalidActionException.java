package exceptions;

public class InvalidActionException extends GameActionException {

    public InvalidActionException() {
        super();
    }

    public InvalidActionException(String s) {
        super(s);
    }
}
