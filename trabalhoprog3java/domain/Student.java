package trabalhoprog3java.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import trabalhoprog3java.domain.activity.ActivityRating;
import trabalhoprog3java.domain.activity.evaluative.EvaluativeActivity;

public class Student extends Person implements Comparable<Student> {

	private long registrationCode;
	private List<EvaluativeActivity> avaliativeActivities;
	private List<ActivityRating> evaluations;

	public Student(long code, String fullName) {
		super(fullName);
		this.registrationCode = code;
		this.avaliativeActivities = new ArrayList<>();
		this.evaluations = new ArrayList<>();
	}

	public long getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(int registrationCode) {
		this.registrationCode = registrationCode;
	}

	public long getStudentReference() {
		return this.registrationCode;
	}

	public String getStudentData() {
		return "\nMatricula: " + this.registrationCode + "\nNome Completo: " + this.getFullName();
	}

	public double calculateAverageEnrolledDisciplinesByPeriod() {
		double disciplineQuantity = super.getAssociatedDisciplines().size();
		double periodQuantity = super.getNumberOfAssociatedPeriods();
		if (periodQuantity == 0) {
			return 0;
		} else {
			return disciplineQuantity / periodQuantity;
		}

	}

	public int getNumberDoneActivities() {
		return this.evaluations.size();
	}
	
	public double calculateAverageStudentRating() {
		double quantityEvaluations = this.evaluations.size();
		double average = 0;
		if(quantityEvaluations == 0) {
			return 0;
		}
		else {
			for(ActivityRating evaluation: evaluations) {
				average += evaluation.getGrade();
			}
			average /= quantityEvaluations;
			return average;		
		}

	}

	@Override
	public int compareTo(Student student) {
//		System.out.println("passou1");
		int compareDoneActivities = student.getNumberDoneActivities() - this.getNumberDoneActivities();
//		System.out.println("passou2");
		if (compareDoneActivities == 0) {
//			System.out.println("passou3");
			return super.getFullName().compareTo(student.getFullName());
		} else {
//			System.out.println("passou4");
			return student.getNumberDoneActivities() - this.getNumberDoneActivities();
		}
	}

	public double calculateAverageNumberDoneActivitiesPerDiscipline() {
		double numberDoneActivities = this.getNumberDoneActivities();
		double numberDisciplines = super.getAssociatedDisciplines().size();

		if (numberDisciplines == 0) {
			return 0;
		} else {
			return numberDoneActivities / numberDisciplines;
		}
	}

	public List<EvaluativeActivity> getAvaliativeActivities() {
		return avaliativeActivities;
	}

	public void setAvaliativeActivities(EvaluativeActivity activity) {
		this.avaliativeActivities.add(activity);
	}

	public List<ActivityRating> getEvaluations() {
		return evaluations;
	}

	public void setEvaluation(ActivityRating evaluation) {
		this.evaluations.add(evaluation);
	}
}
