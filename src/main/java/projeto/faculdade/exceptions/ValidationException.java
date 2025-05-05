package projeto.faculdade.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String msg) {
        super(msg,null,false,false);
    }
}
