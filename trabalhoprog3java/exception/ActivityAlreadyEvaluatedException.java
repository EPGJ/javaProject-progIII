package trabalhoprog3java.exception;

import java.io.Serializable;

public class ActivityAlreadyEvaluatedException extends Exception  implements Serializable {
	private long studentReference;
	private int activityReference;
	private String disciplineReference;
	
	public ActivityAlreadyEvaluatedException(long studentReference,int activityNumber,String disciplineReference ) {
		super("Avaliacao repetida: estudante " + studentReference + " para atividade " + activityNumber + " de " + disciplineReference );
		this.studentReference = studentReference;
		this.activityReference = activityNumber;
		this.disciplineReference = disciplineReference;
		
	}

	public long getStudentReference() {
		return studentReference;
	}

	public int getActivityReference() {
		return activityReference;
	}

	public String getDisciplineReference() {
		return disciplineReference;
	}
	
}
