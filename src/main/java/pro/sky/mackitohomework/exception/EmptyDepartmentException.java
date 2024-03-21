package pro.sky.mackitohomework.exception;

public class EmptyDepartmentException extends RuntimeException{
    public EmptyDepartmentException() {
    }

    public EmptyDepartmentException(String message) {
        super(message);
    }

    public EmptyDepartmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyDepartmentException(Throwable cause) {
        super(cause);
    }

    public EmptyDepartmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
