package trabalhoprog3java.exception;

public class InputMismatchException extends Exception {

	public InputMismatchException(String line){
		super("Dado inválido: " + line);
	}
	
}
