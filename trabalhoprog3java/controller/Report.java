package trabalhoprog3java.controller;
import java.io.Serializable;

import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.Period;
import trabalhoprog3java.domain.Student;
import trabalhoprog3java.domain.Teacher;
import trabalhoprog3java.domain.activity.Activity;

public class Report implements Serializable {

	public void periodsReport(Period period) {
	
		
		period.sort();		
		period.getDisciplines().forEach(discipline -> System.out.println("Codigo da disciplina: " + discipline.getCode()
				+ "\nNome da disciplina: " + discipline.getName() 
				+ "\nNome do docente: " + discipline.getResponsableTeacher().getFullName() + "\nEmail do docente: "
				+ discipline.getResponsableTeacher().getLogin() + "@ufes.br"
				+ "\nQuantidade de estudantes matriculados: " + discipline.getEnrolledStudents().size()
				+ "\nQuantidade de atividades registradas: " + discipline.getActivities().size()+"\n\n"));
	}

	public void teachersReport(Teacher teacher) {
		double percentageSynchronousActivities = teacher.calculatePercentageSynchronousActivities();
		double percentageAsyncronousActivities;
		if(percentageSynchronousActivities == -1) {
			percentageSynchronousActivities = 0;
			percentageAsyncronousActivities = 0;
		}
		else {
			percentageAsyncronousActivities = 100.0 - percentageSynchronousActivities;
		}
		
		System.out.println("Nome: " + teacher.getFullName());
		System.out.println("Numero de disciplinas associadas: " + teacher.getAssociatedDisciplines().size());
		System.out.println("Numero de periodos que possui disciplinas associadas: "+ teacher.getNumberOfAssociatedPeriods());
		System.out.println("Media de atividades por disciplina: " + String.format("%.1f",teacher.calculateAvarageActivitiesPerDiscipline()));
		System.out.println("Percentual de atividades sincronas: "+ String.format("%.0f",percentageSynchronousActivities) + "%");
		System.out.println("Percentual de atividades assincronas: "+ String.format("%.0f",percentageAsyncronousActivities) + "%");
		System.out.println("Media de notas recebidas em avaliacoes discentes: " + String.format("%.1f",teacher.calculateAverageStudentsRating())+"\n");

	}

	public void studentsReport(Student student) {

		System.out.println("Matricula: " + student.getRegistrationCode());
		System.out.println("Nome: " + student.getFullName());
		System.out.println("Media de disciplinas matriculadas por periodo academico: "+ String.format("%.1f",student.calculateAverageEnrolledDisciplinesByPeriod()));
		System.out.println("Media de avaliacoes realizadas por disciplina: "+ String.format("%.1f",student.calculateAverageNumberDoneActivitiesPerDiscipline())+"\n");
	}

	public void teachersDisciplinesReport(Teacher teacher) {

		System.out.println("\n\n\tEstatisticas das disciplinas de um professor \n");
		System.out.println("Nome do professor: " + teacher.getFullName());
		System.out.println("DISCIPLINAS: \n");

		for(Discipline discipline: teacher.getAssociatedDisciplines()) {
			System.out.println("Periodo academico: "+ discipline.getPeriod().getPeriodReference()
					+ "\nCodigo da disciplina: " + discipline.getCode() 
					+ "\nNome da disciplina: " + discipline.getName() 
					+ "\nNumero total de atividades: " + discipline.getActivities().size()
					+ "\nPercentual de atividades sincronas: " + discipline.calculatePercentageSynchronousActivities()
					+ "\nPercentual de atividades assincronas: " + (100 -discipline.calculatePercentageSynchronousActivities())
					+ "\nCarga horaria da disciplina: " + discipline.getWorkLoad()
				);
			System.out.println("\n\tAtividade");
			for(Activity activity: discipline.getActivities()) {
				if(activity.isAvaliative()) {
					System.out.println("Data: "+ activity.getActivityData());
					System.out.println("Nome: "+ activity.getName());
				}
			}
			
		}

	}

}
