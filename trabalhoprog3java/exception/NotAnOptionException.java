package trabalhoprog3java.exception;

import java.io.Serializable;

public class NotAnOptionException extends Exception  implements Serializable {

	public NotAnOptionException(int option){
		super("Dado inválido: " + option+".");
	}
	
}
