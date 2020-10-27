package trabalhoprog3java.controller;

import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.Period;
import trabalhoprog3java.domain.Teacher;
import trabalhoprog3java.domain.activity.Activity;
import trabalhoprog3java.domain.activity.ActivityRating;
import trabalhoprog3java.domain.activity.Lesson;
import trabalhoprog3java.domain.activity.Student;
import trabalhoprog3java.domain.activity.Test;
import trabalhoprog3java.domain.activity.Work;
import trabalhoprog3java.domain.study.Material;
import trabalhoprog3java.domain.study.Study;
import trabalhoprog3java.exception.InvalidReferenceException;
import trabalhoprog3java.exception.ReferenceAlredyExistsException;

public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	Map<String, Period> periods = new HashMap<>();
	Map<String, Discipline> disciplines = new HashMap<>();
	Map<Integer, Student> students = new HashMap<>();
	Map<String, Teacher> teachers = new HashMap<>();
	List<Activity> activities = new ArrayList<>();
	Utils util;
	Report report;
	ReadData readData;
	
	public Menu(ReadData readData) {
		this.readData = readData;
		this.util = new Utils(readData);
		this.report  = new Report();
	}

	public void printMenuOptions() {
		System.out.printf("\n\tMENU\n" + "1 - Cadastro de períodos \n" + "2 - Cadastro de docentes  \n"
				+ "3 - Cadastro de disciplinas \n" + "4 - Cadastro de estudantes \n"
				+ "5 - Matrícula de estudantes em disciplinas \n" + "6 - Cadastro de atividades de disciplina \n"
				+ "7 - Avaliação de atividade por parte de estudante \n" + "8 - Relatorios \n" + "9 - Salvar \n"
				+ "10 - Carregar \n" + "11 - Sair do programa \n" + "Entre com a sua opcao: ");
	}

	public void printItemOptions(String items) {
		String[] itemOptions = items.split("\n");
		int i = 1;

		
		System.out.printf("\n\n\t SUBMENU\n");

		for (String item : itemOptions) {
			System.out.println(i + " - " + item);
			i++;
		}
		System.out.printf(i + " - Voltar ao menu principal \nDigite sua opcao: ");

	}

	public void listPeriods() {
		if (periods.size() > 0) {
			System.out.println("\n\nPeriodos registrados: ");
			periods.forEach((key, period) -> System.out.println(period.getPeriodData()));
		}

	}

	public void listStudents() {
		if (students.size() > 0) {
			System.out.println("\n\nEstudantes cadastrados: ");
			students.forEach((key, student) -> System.out.println(student.getStudentData()));

		}
	}
	
	public void listTeachers() {
		if (teachers.size() > 0) {
			System.out.println("\n\nDocentes cadastrados: ");
			teachers.forEach((key, teacher) -> System.out.println(teacher.getTeacherData()));

		}
	}

	public void listDisciplines() {
		if (disciplines.size() > 0) {
			System.out.println("\n\nDisciplinas cadastradas: ");

			disciplines.forEach((key, discipline) -> System.out.println(discipline.getDisciplineData()));

		}
	}
	
	public void listActivities() {
		if (!activities.isEmpty()) {
			System.out.println("\n\nAtividades cadastradas: ");
			activities.forEach(activity -> System.out.println(activity.getActivityData()));
		}
	}
	
	public void periodRegister() {
		try {
			listPeriods();
			printItemOptions("Registrar novo periodo\n");
			int userDecision = readData.readUserDecision(2); // o usuario possui duas opcoes de escolha

			if (userDecision == 1) {
				System.out.printf("\nAno: ");
				int year = readData.readYear();

				if (year != -1) {

					System.out.printf("Semestre: ");
					char semester = readData.readChar();

					if (semester != 0) {

						Period period = new Period(year, semester);

						if (periods.get(period.getPeriodReference()) != null) {
							throw new ReferenceAlredyExistsException(period.getPeriodReference());
						}
						periods.put(period.getPeriodReference(), period);
						System.out.println("Sucesso ao cadastrar novo periodo");
					}
				}
			}
			
			
		} catch (ReferenceAlredyExistsException e) {
			System.out.println(e.getMessage());
		}
	}

	public void teacherRegister() {
		try {
			listTeachers();
			printItemOptions("Registrar novo professor\n");
			int userDecision = readData.readUserDecision(2); // o usuario possui duas opcoes de escolha
			if (userDecision == 1) {

				System.out.printf("\nLogin: ");
				String login = readData.readLogin();

				
				if (login != "invalid") {
					
					System.out.printf("Nome Completo: ");
					String fullName = readData.readString();
					System.out.printf("Deseja adicionar pagina web? \n1 - Sim\n2 - Nao\nDigite sua escolha: ");
					int response = readData.readUserDecision(2);

					if (response == 1) {

						System.out.printf("Pagina Web: ");
						String webPage = readData.readString();
						

						Teacher teacher = new Teacher(login, fullName, webPage);
						if (teachers.get(teacher.getTeacherReference()) != null) {
							throw new ReferenceAlredyExistsException(teacher.getTeacherReference());
						}
						teachers.put(teacher.getTeacherReference(), teacher);

					} else {
						Teacher teacher = new Teacher(login, fullName);
						if (teachers.get(teacher.getTeacherReference()) != null) {
							throw new ReferenceAlredyExistsException(teacher.getTeacherReference());
						}
						teachers.put(teacher.getTeacherReference(), teacher);
					}

					System.out.println("Sucesso ao cadastrar novo Professor");
				}
			}
		} catch (ReferenceAlredyExistsException e) {
			System.out.println(e.getMessage());
		}

	}

	public void disciplineRegister() {
		try {
			listDisciplines();
			printItemOptions("Registrar nova disciplina\n");

			int userDecision = readData.readUserDecision(2); // o usuario possui duas opcoes de escolha
			if (userDecision == 1) {

				System.out.printf("\nCodigo: ");
				String code = readData.readString();

				System.out.printf("Nome: ");
				String name = readData.readString();

				System.out.printf("Periodo (ex: 2019/1): ");
				String periodReference = readData.readPeriod();

				if (periodReference != "invalid") {

					System.out.printf("Login institucional do professor responsavel: ");
					String responsableTeacher = readData.readLogin();
					if (responsableTeacher != "invalid") {
						if (teachers.get(responsableTeacher) == null) {
							throw new InvalidReferenceException(responsableTeacher);
						}

						Period period = periods.get(periodReference);
						if (period == null) {
							throw new InvalidReferenceException(periodReference);
						}
						Discipline discipline = new Discipline(code, name, period, teachers.get(responsableTeacher));
						if (disciplines.get(discipline.getDisciplineReference()) != null) {
							throw new ReferenceAlredyExistsException(discipline.getDisciplineReference());
						}

						disciplines.put(discipline.getDisciplineReference(), discipline);
						period.setDiscipline(discipline);
						System.out.println("Sucesso ao cadastrar nova disciplina");
					}
				}
			}
		} catch (InvalidReferenceException e) {
			System.out.println("Referencia nao cadastrada no sistema: " + e.getReference());

		} catch (ReferenceAlredyExistsException e) {
			System.out.println(e.getMessage());

		}

	}

	public void studentRegister() {
		try {
			listStudents();
			printItemOptions("Registrar novo estudante\n");
			int userDecision = readData.readUserDecision(2); // o usuario possui duas opcoes de escolha

			if (userDecision == 1) {

				System.out.printf("\nMatricula: ");
				int code = readData.readInt();
				if (code != -1) {

					System.out.printf("Nome completo: ");
					String fullName = readData.readString();

					Student student = new Student(code, fullName);
					if (students.get(student.getStudentReference()) != null) {
						throw new ReferenceAlredyExistsException(String.valueOf(code));
					}

					students.put(student.getStudentReference(), student);
					System.out.println("Sucesso ao cadastrar novo estudante");
				}
			}

		} catch (ReferenceAlredyExistsException e) {
			System.out.println(e.getMessage());
		}

	}

	public void enrollStudent() {
		try {
			printItemOptions("Matricular estudante\n");
			int userDecision = readData.readUserDecision(2); // o usuario possui duas opcoes de escolha

			if (userDecision == 1) {

				System.out.printf("\nCodigo de matricula do estudante: ");
				int studentCode = readData.readInt();
				if (studentCode != -1) {
					System.out.printf("Codigo da disciplina: ");
					String disciplineCode = readData.readString();

					System.out.printf("Periodo da disciplina: ");
					String disciplinePeriod = readData.readString();

					Discipline discipline = util.findDiscipline(disciplineCode, disciplinePeriod, disciplines);

					if (discipline == null)
						throw new InvalidReferenceException(disciplineCode + "-" + disciplinePeriod);
					else {
						Student student = util.findStudent(studentCode, students);
						if (student == null) {
							throw new InvalidReferenceException(String.valueOf(studentCode));

						}
						if (util.isEnrolled(discipline, student)) {
							throw new ReferenceAlredyExistsException(String.valueOf(student.getStudentReference()));
						}

						discipline.enrollStudent(student);
						System.out.println("Sucesso ao matricular estudante");
					}
				}

			}

		} catch (InvalidReferenceException e) {
			System.out.println(e.getMessage());
		} catch (ReferenceAlredyExistsException e) {
			System.out.println("Matricula repetida: " + e.getReference());
		}

	}

	public void activityRegister() {
		listActivities();
		printItemOptions("Cadastrar aula\n" + "Cadastrar estudo\n" + "Cadastrar trabalho\n" + "Cadastrar prova\n");
		int userDecision = readData.readUserDecision(5); // o usuario possui cinco opcoes de escolha

		try {
			switch (userDecision) {
			case 1:
				this.lessonRegister();
				break;

			case 2:
				this.studyRegister();
				break;

			case 3:
				this.workRegister();
				break;

			case 4:
				this.testRegister();
				break;

			}

		} catch (InvalidReferenceException e) {
			System.out.println("Referencia nao cadastrada no sistema: " + e.getReference());
		}

	}

	public void testRegister() throws InvalidReferenceException {

		System.out.printf("\nTitulo da prova: ");
		String name = readData.readString();

		System.out.printf("Codigo da disciplina: ");
		String disciplineCode = readData.readString();

		System.out.printf("Periodo da disciplina: ");
		String disciplinePeriod = readData.readString();

		Discipline discipline = util.findDiscipline(disciplineCode, disciplinePeriod, this.disciplines);
		if (discipline == null) {
			throw new InvalidReferenceException(disciplineCode + "-" + disciplinePeriod);
		} else {

			System.out.printf("Data da prova ( DD/MM/AAAA ): ");
			String date = readData.readString();

			System.out.printf("horario da aula ( HH:MM ): ");
			String time = readData.readString();

			System.out.printf("\nConteudo da prova: ");
			String testContent = readData.readString();

			Test test = new Test(name, discipline, date, time, testContent);

			discipline.setActivity(test);
			test.setActivityNumber(discipline.getActivities().size());

			activities.add(test);
		}

	}

	public void lessonRegister() throws InvalidReferenceException {

		System.out.printf("\nTema da aula: ");
		String name = readData.readString();

		System.out.printf("Codigo da disciplina: ");
		String disciplineCode = readData.readString();

		System.out.printf("Periodo da disciplina: ");
		String disciplinePeriod = readData.readString();

		Discipline discipline = this.util.findDiscipline(disciplineCode, disciplinePeriod, this.disciplines);
		if (discipline == null) {
			throw new InvalidReferenceException(disciplineCode + "-" + disciplinePeriod);
		} else {

			System.out.printf("Data da aula ( DD/MM/AAAA ): ");
			String date = readData.readString();

			System.out.printf("horario da aula ( HH:MM ): ");
			String time = readData.readString();

			Lesson lesson = new Lesson(name, discipline, date, time);
			if (discipline != null) {
				discipline.setActivity(lesson);
				lesson.setActivityNumber(discipline.getActivities().size());

			}
			this.activities.add(lesson);
		}
	}

	public void workRegister() throws InvalidReferenceException {

		System.out.printf("\nTitulo do trabalho: ");
		String name = readData.readString();

		System.out.printf("Codigo da disciplina: ");
		String disciplineCode = readData.readString();

		System.out.printf("Periodo da disciplina: ");
		String disciplinePeriod = readData.readString();

		Discipline discipline = this.util.findDiscipline(disciplineCode, disciplinePeriod, this.disciplines);
		if (discipline == null) {
			throw new InvalidReferenceException(disciplineCode + "-" + disciplinePeriod);
		} else {

			System.out.printf("Data de entrega ( DD/MM/AAAA ): ");
			String date = readData.readString();

			System.out.printf("Numero maximo de pessoas por grupo: ");
			int maxNumber = readData.readInt();

			System.out.printf("Carga horaria: ");
			double workload = readData.readDouble();

			Work newWork = new Work(name, discipline, date, maxNumber, workload);

			discipline.setActivity(newWork);
			newWork.setActivityNumber(discipline.getActivities().size());

			activities.add(newWork);
		}

	}

	public void studyRegister() throws InvalidReferenceException {
		System.out.printf("\nTema a ser estudado: ");
		String name = readData.readString();

		System.out.printf("Codigo da disciplina: ");
		String disciplineCode = readData.readString();

		System.out.printf("Periodo da disciplina: ");
		String disciplinePeriod = readData.readString();

		Discipline discipline = this.util.findDiscipline(disciplineCode, disciplinePeriod, this.disciplines);
		if (discipline == null) {
			throw new InvalidReferenceException(disciplineCode + "-" + disciplinePeriod);
		} else {
			List<Material> materials = new ArrayList<>();
			int option = 1;
			do {

				materials.add(materialRegister());
				System.out.println("\nDeseja cadastrar outro material?\n1 - Sim\n2 - Nao\nDigite sua escolha: ");
				option = readData.readInt();


			} while (option == 1);

			Study study = new Study(name, discipline, materials);

			discipline.setActivity(study);
			study.setActivityNumber(discipline.getActivities().size());

			activities.add(study);
		}

	}

	public Material materialRegister() {
		System.out.printf("\nNome do material: ");
		String name = readData.readString();

		System.out.printf("Link para o material: ");
		String link = readData.readString();

		Material material = new Material(name, link);
		return material;
	}

	public void activityRating() {
		printItemOptions("Avaliar atividade\n");
		int userDecision = readData.readUserDecision(2); // o usuario possui cinco opcoes de escolha

		if (userDecision == 1) {

			System.out.printf("\nCodigo de matricula do estudante: ");
			int studentCode = readData.readInt();


			System.out.printf("Codigo da disciplina: ");
			String disciplineCode = readData.readString();

			System.out.printf("Periodo da disciplina: ");
			String disciplinePeriod = readData.readString();

			System.out.printf("\nNumero da atividade: ");
			int activityNumber = readData.readInt();

			System.out.printf("\nNota para a atividade: ");
			double activityGrade = readData.readDouble();

			Discipline discipline = util.findDiscipline(disciplineCode, disciplinePeriod, disciplines);
			if (discipline == null)
				System.out.println("disciplina nao encontrada");
			else {
				Student student = util.findStudent(studentCode, students);
				if (student != null) {
					ActivityRating activityRating = new ActivityRating(student, discipline, activityGrade);
					discipline.getActivities().get(activityNumber - 1).setSudentsEvaluation(activityRating);
					System.out.println("sucesso ao avaliar atividade: ");
				}
			}

		}

	}

	public void report() {
		printItemOptions("Visao geral do periodo academico\n" + "Estatisticas dos docentes\n"
				+ "Estatisticas dos estudantes\n" + "Estatisticas das disciplinas de um docente\n");

		int userDecision = readData.readUserDecision(5); // o usuario possui cinco opcoes de escolha
		try {
			switch (userDecision) {

			case 1:
				listPeriods();
				System.out.println("Digite o periodo cadastrado no formato ANO/SEMESTRE (ex: 2019/1) ");
				String periodReference = readData.readString();
				Period period = periods.get(periodReference);
				if (period == null) {
					throw new InvalidReferenceException(periodReference);
				} 
				else {
					report.periodsReport(period);
					util.pressAnyKeyToContinue();
				}
				
				break;

			case 2:
				System.out.println("\n\n\tEstatisticas dos docentes");
				System.out.println("\nPROFESSORES: \n");

				if (teachers.size() > 0) {
					for (Map.Entry<String, Teacher> teacher : teachers.entrySet()) {
						teacher.getValue().findAssociatedDisciplines(disciplines);
						report.teachersReport(teacher.getValue());
					}
				}
				System.out.println("\n Digite qualquer coisa para continuar: ");

				break;

			case 3:
				System.out.println("\n\n\tEstatisticas dos estudantes");
				System.out.println("\nEstudantes: \n");

				if (students.size() > 0) {
					for (Entry<Integer, Student> student : students.entrySet()) {
						student.getValue().findAssociatedDisciplines(disciplines);
						report.studentsReport(student.getValue());
					}
				}
				System.out.println("\n Digite qualquer coisa para continuar: ");

				break;

			case 4:

				listTeachers();
				System.out.println("Digite o login institucional do docente: ");
				String teacherReference = readData.readString();
				Teacher teacher = teachers.get(teacherReference);
				if (teacher != null) {

					report.teachersDisciplinesReport(teacher);
					System.out.println("\n Digite qualquer coisa para continuar: ");


				}

				break;

			}

		} catch (InvalidReferenceException e) {
			System.out.println("Referencia nao cadastrada no sistema: " + e.getReference());
		}

	}

}
