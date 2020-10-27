package trabalhoprog3java.exception;

import java.io.Serializable;

public class ActivityAlreadyEvaluatedException extends Exception  implements Serializable {
	private int studentReference;
	private String activityReference;
	private String disciplineReference;
	
	public ActivityAlreadyEvaluatedException(int i,int activityNumber,String disciplineReference ) {
		super("Avaliacao repetida: estudante " + i+ " para atividade " + activityNumber + " de " + disciplineReference );
	}

	public int getStudentReference() {
		return studentReference;
	}

	public String getActivityReference() {
		return activityReference;
	}

	public String getDisciplineReference() {
		return disciplineReference;
	}
	
}
