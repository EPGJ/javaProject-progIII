package trabalhoprog3java.exception;

import java.io.Serializable;

public class InvalidReferenceException extends Exception  implements Serializable  {

	public InvalidReferenceException(String reference) {
		super("Referencia invalida: " + reference);
	}
	
}
