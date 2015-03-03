package sample.mediaplayer.service;

public class MediaException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public MediaException(String message){
		super(message);
	}
	
	public MediaException(String message, Throwable t){
		super(message, t);
	}

}
