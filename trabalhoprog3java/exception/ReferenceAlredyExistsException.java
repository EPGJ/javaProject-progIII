package trabalhoprog3java.exception;

import java.io.Serializable;

public class ReferenceAlredyExistsException extends Exception  implements Serializable {
	private String reference;
	
	public ReferenceAlredyExistsException(String reference){
		super("Cadastro repetido: " + reference);
		this.reference = reference;
	}

	public String getReference() {
		return reference;
	}
	
}
