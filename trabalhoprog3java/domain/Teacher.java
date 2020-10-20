package trabalhoprog3java.domain;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import trabalhoprog3java.domain.activity.Activity;
import trabalhoprog3java.domain.activity.ActivityRating;

public class Teacher extends Person {

	private String login;
	private String webPage;
	

	public Teacher() {
	}

	public Teacher(String login, String fullName) {
		super(fullName);
		this.login = login;
	}

	public Teacher(String login, String fullName, String webPage) {
		this(login, fullName);
		this.webPage = webPage;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getWebPage() {
		return webPage;
	}

	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}

	public String getTeacherReference() {
		return this.login;
	}
	

	public String getTeacherData() {
		if (this.webPage != null) {
			return "\nNome: " + this.getFullName() + "\nPagina Web: " + this.getWebPage();
		} else {
			return "\nNome: " + this.getFullName();
		}

	}

	@Override
	public void findAssociatedDisciplines(Map<String, Discipline> disciplines) {
		for (Map.Entry<String, Discipline> discipline : disciplines.entrySet()) {
			if (discipline.getValue().getResponsableTeacher() == this) {
				super.getAssociatedDisciplines().add(discipline.getValue());
			}
		}
	}

	public double calculateAvarageActivitiesByDiscipline() {
		double average = 0;

		for (Discipline discipline : super.getAssociatedDisciplines()) {
			average += discipline.getActivities().size();
		}
		average /= super.getAssociatedDisciplines().size();
		return average;
	}

	public double calculatePercentageSynchronousActivities() {
		double totalActivities = 0;
		double totalSynchronousActivities = 0;

		for (Discipline discipline : super.getAssociatedDisciplines()) {

			totalActivities += discipline.getActivities().size();
			totalSynchronousActivities += discipline.getTotalSynchronousActivities();

		}

		return ((totalSynchronousActivities / totalActivities) * 100);
	}

	

	public double calculateAverageStudentsRating() {
		List<ActivityRating> studentsRating = new ArrayList<>();

		for (Discipline discipline : super.getAssociatedDisciplines()) {
			for(Activity activity : discipline.getActivities()) {
				studentsRating.addAll(activity.getstudentsRating());
			}
		}
		
		return averageStudentsRatingGrade(studentsRating);
	}
	
	public double averageStudentsRatingGrade(List<ActivityRating> studentsRating) {
		double numberEvaluations= studentsRating.size();
		double sumEvaluations=0;
		
		for(ActivityRating activityRating: studentsRating) {
			sumEvaluations+=activityRating.getGrade();
		}
		
		return (sumEvaluations/numberEvaluations);
	}

}
