package ua.nure.samoylenko.exception;

public class AppException extends RuntimeException{
	
    public AppException(){
        super();
    }

    public AppException(String text){
        super(text);
    }

    public AppException(String text, Throwable cause){
        super(text, cause);
    }

}
