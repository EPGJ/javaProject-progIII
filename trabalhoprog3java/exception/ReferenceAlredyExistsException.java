package trabalhoprog3java.exception;

import java.io.Serializable;

public class ReferenceAlredyExistsException extends Exception  implements Serializable {

	public ReferenceAlredyExistsException(String reference){
		super("Cadastro repetido: " + reference);
	}
	
}
