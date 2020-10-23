package trabalhoprog3java.exception;

import java.io.Serializable;

public class NotCharException extends Exception  implements Serializable  {

	public NotCharException(String line){
		super("Dado inválido: " + line);
	}
	
}
