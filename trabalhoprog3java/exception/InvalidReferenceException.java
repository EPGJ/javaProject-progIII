package trabalhoprog3java.exception;

import java.io.Serializable;

public class InvalidReferenceException extends Exception  implements Serializable  {
	private String reference;
	
	public InvalidReferenceException(String reference) {
		super("Referencia invalida: " + reference);
		this.reference= reference;
	}

	public String getReference() {
		return reference;
	}
	
}
