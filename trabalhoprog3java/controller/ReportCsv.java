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
		StringBuilder sb = new StringBuilder();
		sb.append("Período;Código Disciplina;Disciplina;Docente Responsável;E-mail Docente;Qtd. Estudantes;Qtd. Atividades\r\n");

		for (Period period : periodsList) {
			period.sort();

			for (Discipline discipline : period.getDisciplines()) {
				sb.append(period.getPeriodReference() + ";" + discipline.getCode() + ";" + discipline.getName() + ";"
						+ discipline.getResponsableTeacher().getFullName() + ";"
						+ discipline.getResponsableTeacher().getLogin() + "@ufes.br" + ";"
						+ discipline.getEnrolledStudents().size() + ";" + discipline.getActivities().size());
			
				sb.append("\r\n");
			}
		}
		   pw.write(sb.toString());
		pw.close();


	}

	public void teachersReport(List<Teacher> teachersList) throws FileNotFoundException {
		
		
		PrintWriter pw = new PrintWriter(new File("2-docentes.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("Docente;Qtd. Disciplinas;Qtd. Períodos;Média Atividades/Disciplina;% Síncronas;% Assíncronas;Média de Notas\r\n");

		for (Teacher teacher : teachersList) {
			double percentageSynchronousActivities = teacher.calculatePercentageSynchronousActivities();
			double percentageAsyncronousActivities;
			if (percentageSynchronousActivities == -1) {
				percentageSynchronousActivities = 0;
				percentageAsyncronousActivities = 0;
			} else {
				percentageAsyncronousActivities = 100.0 - percentageSynchronousActivities;
			}
			
			sb.append(
						teacher.getFullName() + ";" +
						teacher.getAssociatedDisciplines().size() + ";" +
						teacher.getNumberOfAssociatedPeriods() + ";" + 
						String.format("%.1f", teacher.calculateAvarageActivitiesPerDiscipline()) + ";" +
						String.format("%.0f", percentageSynchronousActivities) + "%;" +
						String.format("%.0f", percentageAsyncronousActivities) + "%;" +
						String.format("%.1f", teacher.calculateAverageStudentsRating()) );
		
			sb.append("\r\n");	
			
		}
		   pw.write(sb.toString());
		pw.close();
		

	}

	public void studentsReport(List<Student> studentList) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new File("3-estudantes.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("Matrícula;Nome;Média Disciplinas/Período;Média Avaliações/Disciplina;Média Notas Avaliações\r\n");

		for (Student student : studentList) {
			
			sb.append(
					student.getRegistrationCode() + ";" +
					student.getFullName() + ";" +
					String.format("%.1f", student.calculateAverageEnrolledDisciplinesByPeriod()) + ";" +
					String.format("%.1f", student.calculateAverageNumberDoneActivitiesPerDiscipline()) + ";" +
					String.format("%.1f", student.calculateAverageStudentRating()));						
		
			sb.append("\r\n");	
			
		}
		   pw.write(sb.toString());
		pw.close();
		
	}

	public void disciplinesReport(List<Discipline> disciplineList) throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter(new File("4-disciplinas.csv"));
		StringBuilder sb = new StringBuilder();
		sb.append("Docente;Período;Código;Nome;Qtd. Atividades;% Síncronas;% Assíncronas;CH;Datas Avaliações\r\n");

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
			
			sb.append(
					discipline.getResponsableTeacher().getLogin() + ";" +
					discipline.getPeriod().getPeriodReference() + ";" +
					discipline.getCode() + ";" +
					discipline.getName() + ";" +
					discipline.getActivities().size() + ";" +
					String.format("%.0f",percentageSynchronousActivities) + "%;" +
					String.format("%.0f",percentageAsyncronousActivities) + "%;" +
					discipline.getWorkLoad()+";");
			
			for (Activity activity : discipline.getActivities()) {
				if (activity.isAvaliative()) {
					EvaluativeActivity activityAux = (EvaluativeActivity) activity;
					sb.append(f.format(activityAux.getDate())+ " ");
				}
			}
			
			
			sb.append("\r\n");	
			
		}
		   pw.write(sb.toString());
		pw.close();
		
		
//		DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
//		double percentageSynchronousActivities = discipline.calculatePercentageSynchronousActivities();
//		double percentageAsyncronousActivities;
//		if (percentageSynchronousActivities == -1) {
//			percentageSynchronousActivities = 0;
//			percentageAsyncronousActivities = 0;
//		} else {
//			percentageAsyncronousActivities = 100.0 - percentageSynchronousActivities;
//		}
//
//		System.out.println("\n\tDisciplina \n");
//		System.out.println("Login do docente: " + discipline.getResponsableTeacher().getLogin()
//				+ "\nPeriodo academico: " + discipline.getPeriod().getPeriodReference() + "\nCodigo da disciplina: "
//				+ discipline.getCode() + "\nNome da disciplina: " + discipline.getName()
//				+ "\nNumero total de atividades: " + discipline.getActivities().size()
//				+ "\nPercentual de atividades sincronas: " + percentageSynchronousActivities
//				+ "\nPercentual de atividades assincronas: " + percentageAsyncronousActivities
//				+ "\nCarga horaria da disciplina: " + discipline.getWorkLoad() + "\n");
//		if (discipline.getActivities().size() > 0) {
//			System.out.println("\n\tAtividades\n");
//		}
//		for (Activity activity : discipline.getActivities()) {
//			if (activity.isAvaliative()) {
//
//				EvaluativeActivity activityAux = (EvaluativeActivity) activity;
//				System.out.println("Data: " + f.format(activityAux.getDate()));
//				System.out.println("Nome: " + activityAux.getName() + "\n");
//			}
//		}

	}

}
