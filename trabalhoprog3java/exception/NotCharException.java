package trabalhoprog3java.exception;

public class NotCharException extends Exception {

	public NotCharException(String line){
		super("Dado inválido: " + line);
	}
	
}
