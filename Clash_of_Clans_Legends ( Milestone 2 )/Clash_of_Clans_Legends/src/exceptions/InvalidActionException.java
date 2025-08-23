package exceptions;

public class InvalidActionException extends GameActionException {
    private static final long serialVersionUID = 1L;

    public InvalidActionException() {
        super();
    }

    public InvalidActionException(String s) {
        super(s);
    }
}
