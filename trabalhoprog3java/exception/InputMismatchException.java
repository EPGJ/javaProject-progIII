package trabalhoprog3java.exception;

import java.io.Serializable;

public class InputMismatchException extends Exception  implements Serializable {

	public InputMismatchException(String line){
		super("Dado inválido: " + line+".");
	}
	
}
