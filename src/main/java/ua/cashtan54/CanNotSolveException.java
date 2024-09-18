package ua.cashtan54;

class CanNotSolveException extends RuntimeException {
    CanNotSolveException(String message) {
        super(message);
    }
    CanNotSolveException(String message, Throwable cause) {
        super(message, cause);
    }
}
