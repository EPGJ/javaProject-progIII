package trabalhoprog3java.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.List;

import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.Period;
import trabalhoprog3java.domain.Student;
import trabalhoprog3java.domain.Teacher;
import trabalhoprog3java.domain.activity.Activity;
import trabalhoprog3java.domain.activity.evaluative.EvaluativeActivity;

public class ReportCsv implements Serializable {

	public void periodsReport(List<Period> periodsList) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File("1-visao-geral.csv"));
		pw.printf("%s%n", "Período;Código Disciplina;Disciplina;Docente Responsável;E-mail Docente;Qtd. Estudantes;Qtd. Atividades");
		
		for (Period period : periodsList) {
			period.sort();

			for (Discipline discipline : period.getDisciplines()) {
				pw.printf("%s%n",period.getPeriodReference() + ";" + discipline.getCode() + ";" + discipline.getName() + ";"
						+ discipline.getResponsableTeacher().getFullName() + ";"
						+ discipline.getResponsableTeacher().getLogin() + "@ufes.br" + ";"
						+ discipline.getEnrolledStudents().size() + ";" + discipline.getActivities().size());
			}
		}
		
		pw.close();

	}

	public void teachersReport(List<Teacher> teachersList) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new File("2-docentes.csv"));
		
		pw.printf("%s%n","Docente;Qtd. Disciplinas;Qtd. Períodos;Média Atividades/Disciplina;% Síncronas;% Assíncronas;Média de Notas");
		
		
		for (Teacher teacher : teachersList) {
			double percentageSynchronousActivities = teacher.calculatePercentageSynchronousActivities();
			double percentageAsyncronousActivities;
			if (percentageSynchronousActivities == -1) {
				percentageSynchronousActivities = 0;
				percentageAsyncronousActivities = 0;
			} else {
				percentageAsyncronousActivities = 100.0 - percentageSynchronousActivities;
			}

			pw.printf("%s%n",teacher.getFullName() + ";" + teacher.getAssociatedDisciplines().size() + ";"
					+ teacher.getNumberOfAssociatedPeriods() + ";"
					+ String.format("%.1f", teacher.calculateAvarageActivitiesPerDiscipline()) + ";"
					+ String.format("%.0f", percentageSynchronousActivities) + "%;"
					+ String.format("%.0f", percentageAsyncronousActivities) + "%;"
					+ String.format("%.1f", teacher.calculateAverageStudentsRating()));
		}

		pw.close();

	}

	public void studentsReport(List<Student> studentList) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new File("3-estudantes.csv"));
		
		pw.printf("%s%n","Matrícula;Nome;Média Disciplinas/Período;Média Avaliações/Disciplina;Média Notas Avaliações");

		for (Student student : studentList) {

			pw.printf("%s%n",student.getRegistrationCode() + ";" + student.getFullName() + ";"
					+ String.format("%.1f", student.calculateAverageEnrolledDisciplinesByPeriod()) + ";"
					+ String.format("%.1f", student.calculateAverageNumberDoneActivitiesPerDiscipline()) + ";"
					+ String.format("%.1f", student.calculateAverageStudentRating()));
		}
	
		pw.close();

	}

	public void disciplinesReport(List<Discipline> disciplineList) throws FileNotFoundException {

		
		PrintWriter pw = new PrintWriter(new File("4-disciplinas.csv"));
		pw.printf("%s%n","Docente;Período;Código;Nome;Qtd. Atividades;% Síncronas;% Assíncronas;CH;Datas Avaliações");

		for (Discipline discipline : disciplineList) {

			DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
			double percentageSynchronousActivities = discipline.calculatePercentageSynchronousActivities();
			double percentageAsyncronousActivities;
			if (percentageSynchronousActivities == -1) {
				percentageSynchronousActivities = 0;
				percentageAsyncronousActivities = 0;
			} else {
				percentageAsyncronousActivities = 100.0 - percentageSynchronousActivities;
			}

			pw.printf("%s",discipline.getResponsableTeacher().getLogin() + ";" + discipline.getPeriod().getPeriodReference()
					+ ";" + discipline.getCode() + ";" + discipline.getName() + ";" + discipline.getActivities().size()
					+ ";" + String.format("%.0f", percentageSynchronousActivities) + "%;"
					+ String.format("%.0f", percentageAsyncronousActivities) + "%;" + String.format("%.0f",discipline.getWorkLoad()) + ";");
			String activitiesData = "";
			for (Activity activity : discipline.getActivities()) {
				if (activity.isAvaliative()) {
					EvaluativeActivity activityAux = (EvaluativeActivity) activity;
					activitiesData+=f.format(activityAux.getDate()) + " ";	
				}
			}
			pw.printf("%s",activitiesData.trim());
			pw.printf("%n");

		}

		pw.close();

	}

}
