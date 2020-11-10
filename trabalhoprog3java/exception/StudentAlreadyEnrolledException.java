package trabalhoprog3java.exception;

import java.io.Serializable;

public class StudentAlreadyEnrolledException extends Exception  implements Serializable {
	private long studentReference;
	private String disciplineReference;
	
	public StudentAlreadyEnrolledException(long studentReference,String disciplineReference ) {
		super("Matrícula repetida: " + studentReference + " em " + disciplineReference+".");
		this.studentReference = studentReference;
		this.disciplineReference = disciplineReference;
		
	}

	public long getStudentReference() {
		return studentReference;
	}

	public String getDisciplineReference() {
		return disciplineReference;
	}
	
}