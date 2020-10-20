package trabalhoprog3java;
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
import trabalhoprog3java.domain.activity.study.Material;
import trabalhoprog3java.domain.activity.study.Study;

public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, Period> periods = new HashMap<>();
	Map<String, Discipline> disciplines = new HashMap<>();
	Map<Integer, Student> students = new HashMap<>();
	Map<String, Teacher> teachers = new HashMap<>();
	List<Activity> activities = new ArrayList<>();
	Utils util = new Utils();
	Report report = new Report();
	transient Scanner input = new Scanner(System.in);

	public void printMenuOptions() {
		System.out.printf("\n\tMENU\n" + "1 - Cadastro de períodos \n" + "2 - Cadastro de docentes  \n"
				+ "3 - Cadastro de disciplinas \n" + "4 - Cadastro de estudantes \n"
				+ "5 - Matrícula de estudantes em disciplinas \n" + "6 - Cadastro de atividades de disciplina \n"
				+ "7 - Avaliação de atividade por parte de estudante \n" + "8 - Relatorios \n"
				+ "9 - Salvar \n"
				+ "10 - Carregar \n"
				+ "11 - Sair do programa \n" + "Entre com a sua opcao: ");
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
	public void listTeachers() {
		if (teachers.size() > 0) {
			System.out.println("\n\nDocentes cadastrados: ");
			teachers.forEach((key, teacher) -> System.out.println(teacher.getTeacherData()));

		}
	}

	public void periodRegister(Scanner input) {
		listPeriods();
		printItemOptions("Registrar novo periodo\n");

		int userDecision = input.nextInt();
		input.nextLine();
		if (userDecision == 1) {
			System.out.printf("\nAno: ");
			int year = input.nextInt();
			input.nextLine();

			System.out.printf("Semestre: ");
			char semester = input.next().charAt(0);
			input.nextLine();

			Period newPeriod = new Period(year, semester);
			periods.put(newPeriod.getPeriodReference(), newPeriod);
			System.out.println("Sucesso ao cadastrar novo periodo");
		}
	}

	public void teacherRegister(Scanner input) {
		listTeachers();
		printItemOptions("Registrar novo professor\n");

		int userDecision = input.nextInt();
		input.nextLine();
		if (userDecision == 1) {

			System.out.printf("\nLogin: ");
			String login = input.nextLine();

			System.out.printf("Nome Completo: ");
			String fullName = input.nextLine();

			System.out.printf("Deseja adicionar pagina web?(S/N): ");
			char response = input.next().charAt(0);
			input.nextLine();

			if (response == 'S' || response == 's') {

				System.out.printf("Pagina Web: ");
				String webPage = input.nextLine();

				Teacher newTeacher = new Teacher(login, fullName, webPage);
				teachers.put(newTeacher.getTeacherReference(), newTeacher);

			} else {
				Teacher newTeacher = new Teacher(login, fullName);
				teachers.put(newTeacher.getTeacherReference(), newTeacher);
			}

			System.out.println("Sucesso ao cadastrar novo Professor");
		}
	}

	public void disciplineRegister(Scanner input) {
		if (disciplines.size() > 0) {
			System.out.println("\n\nDisciplinas cadastradas: ");

			disciplines.forEach((key, discipline) -> System.out.println(discipline.getDisciplineData()));

		}
		printItemOptions("Registrar nova disciplina\n");

		int userDecision = input.nextInt();
		input.nextLine();
		if (userDecision == 1) {

			System.out.printf("\nCodigo: ");
			String code = input.nextLine();

			System.out.printf("Nome: ");
			String name = input.nextLine();

			System.out.printf("Periodo (ex: 2019/1): ");
			String periodReference = input.nextLine();

			System.out.printf("Login institucional do professor responsavel: ");
			String responsableTeacher = input.nextLine();

			Period period = periods.get(periodReference);
			if(period !=null) {
				System.out.println("achou");
			}
			else{
				System.out.println("nao achou");
			}
			Discipline newDiscipline = new Discipline(code, name, period, teachers.get(responsableTeacher));
			disciplines.put(newDiscipline.getDisciplineReference(), newDiscipline);
			period.setDiscipline(newDiscipline);
			System.out.println("Sucesso ao cadastrar nova disciplina");
		}
	}

	public void studentRegister(Scanner input) {
		if (students.size() > 0) {
			System.out.println("\n\nEstudantes cadastrados: ");
			students.forEach((key, student) -> System.out.println(student.getStudentData()));

		}

		printItemOptions("Registrar novo estudante\n");
		int userDecision = input.nextInt();
		input.nextLine();
		if (userDecision == 1) {

			System.out.printf("\nMatricula: ");
			int code = input.nextInt();
			input.nextLine();

			System.out.printf("Nome completo: ");
			String fullName = input.nextLine();

			Student newStudent = new Student(code, fullName);
			students.put(newStudent.getStudentReference(), newStudent);
			System.out.println("Sucesso ao cadastrar novo estudante");
		}
	}

	public void enrollStudent(Scanner input) {
		printItemOptions("Matricular estudante\n");
		int userDecision = input.nextInt();
		input.nextLine();
		if (userDecision == 1) {

			System.out.printf("\nCodigo de matricula do estudante: ");
			int studentCode = input.nextInt();
			input.nextLine();

			System.out.printf("Codigo da disciplina: ");
			String disciplineCode = input.nextLine();

			System.out.printf("Periodo da disciplina: ");
			String disciplinePeriod = input.nextLine();

			Discipline discipline = util.findDiscipline(disciplineCode, disciplinePeriod, disciplines);
			
			if (discipline == null)
				System.out.println("disciplina nao encontrada");
			else {
				Student student = util.findStudent(studentCode, students);
				if (student != null) {
					discipline.enrollStudent(student);
					System.out.println("Sucesso ao matricular estudante");
				}
				else {
					System.out.println("Erro ao matricular estudante");
				}
			}

		}
	}

	public void activityRegister(Scanner input) {
		if (!activities.isEmpty()) {
			System.out.println("\n\nAtividades cadastradas: ");
			activities.forEach(activity -> System.out.println(activity.getActivityData()));
		}
		printItemOptions("Cadastrar aula\n" + "Cadastrar estudo\n" + "Cadastrar trabalho\n" + "Cadastrar prova\n");
		int userDecision = input.nextInt();
		input.nextLine();

		switch (userDecision) {

		case 1:
			this.lessonRegister(input);
			break;

		case 2:
			this.studyRegister(input);
			break;

		case 3:
			this.workRegister(input);
			break;

		case 4:
			this.testRegister(input);
			break;

		}
	}

	public void testRegister(Scanner input) {
		System.out.printf("\nTitulo da prova: ");
		String name = input.nextLine();

		System.out.printf("Codigo da disciplina: ");
		String disciplineCode = input.nextLine();

		System.out.printf("Periodo da disciplina: ");
		String disciplinePeriod = input.nextLine();

		System.out.printf("Data da prova ( DD/MM/AAAA ): ");
		String date = input.nextLine();

		System.out.printf("horario da aula ( HH:MM ): ");
		String time = input.nextLine();

		System.out.printf("\nConteudo da prova: ");
		String testContent = input.nextLine();

		Test newTest = new Test(name, disciplineCode, date, time, testContent);
		Discipline discipline = util.findDiscipline(disciplineCode, disciplinePeriod, this.disciplines);

		if (discipline != null) {
			discipline.setActivity(newTest);
			newTest.setActivityNumber(discipline.getActivities().size());
		}
		activities.add(newTest);

	}

	public void lessonRegister(Scanner input) {
		System.out.printf("\nTema da aula: ");
		String name = input.nextLine();

		System.out.printf("Codigo da disciplina: ");
		String disciplineCode = input.nextLine();

		System.out.printf("Periodo da disciplina: ");
		String disciplinePeriod = input.nextLine();

		System.out.printf("Data da aula ( DD/MM/AAAA ): ");
		String date = input.nextLine();

		System.out.printf("horario da aula ( HH:MM ): ");
		String time = input.nextLine();
        
		System.out.println("passou1");
		Lesson newLesson = new Lesson(name, disciplineCode, date, time);
		System.out.println("passou2");
		Discipline discipline = this.util.findDiscipline(disciplineCode, disciplinePeriod, this.disciplines);
		System.out.println("passou3");

		
		if (discipline != null) {
			System.out.println("passou4");
			discipline.setActivity(newLesson);
			System.out.println("passou5");
			newLesson.setActivityNumber(discipline.getActivities().size());
			System.out.println("passou6");
		}
		System.out.println("passou7");
		this.activities.add(newLesson);
		System.out.println("passou8");
	}

	public void workRegister(Scanner input) {

		System.out.printf("\nTitulo do trabalho: ");
		String name = input.nextLine();

		System.out.printf("Codigo da disciplina: ");
		String disciplineCode = input.nextLine();

		System.out.printf("Periodo da disciplina: ");
		String disciplinePeriod = input.nextLine();

		System.out.printf("Data de entrega ( DD/MM/AAAA ): ");
		String date = input.nextLine();

		System.out.printf("Numero maximo de pessoas por grupo: ");
		int maxNumber = input.nextInt();
		input.nextLine();

		System.out.printf("Carga horaria: ");
		double workload = input.nextDouble();
		input.nextLine();

		Work newWork = new Work(name, disciplineCode, date, maxNumber, workload);

		Discipline discipline = util.findDiscipline(disciplineCode, disciplinePeriod, disciplines);

		if (discipline != null) {
			discipline.setActivity(newWork);
			newWork.setActivityNumber(discipline.getActivities().size());
		}
		activities.add(newWork);

	}

	public void studyRegister(Scanner input) {
		System.out.printf("\nTema a ser estudado: ");
		String name = input.nextLine();

		System.out.printf("Codigo da disciplina: ");
		String disciplineCode = input.nextLine();

		System.out.printf("Periodo da disciplina: ");
		String disciplinePeriod = input.nextLine();

		List<Material> materials = new ArrayList<>();
		int option = 1;
		do {

			materials.add(materialRegister(input));
			System.out.println("\nDeseja cadastrar outro material?\n1 - Sim\n2 - Nao\nDigite sua escolha: ");
			option = input.nextInt();
			input.nextLine();

		} while (option == 1);

		Study newStudy = new Study(name, disciplineCode, materials);
		Discipline discipline = util.findDiscipline(disciplineCode, disciplinePeriod, disciplines);

		if (discipline != null) {
			discipline.setActivity(newStudy);
			newStudy.setActivityNumber(discipline.getActivities().size());
		}
		activities.add(newStudy);

	}

	public Material materialRegister(Scanner input) {
		System.out.printf("\nNome do material: ");
		String name = input.nextLine();

		System.out.printf("Link para o material: ");
		String link = input.nextLine();

		Material material = new Material(name, link);
		return material;
	}

	public void activityRating(Scanner input) {
		printItemOptions("Avaliar atividade\n");
		int userDecision = input.nextInt();
		input.nextLine();
		if (userDecision == 1) {

			System.out.printf("\nCodigo de matricula do estudante: ");
			int studentCode = input.nextInt();
			input.nextLine();

			System.out.printf("Codigo da disciplina: ");
			String disciplineCode = input.nextLine();

			System.out.printf("Periodo da disciplina: ");
			String disciplinePeriod = input.nextLine();

			System.out.printf("\nNumero da atividade: ");
			int activityNumber = input.nextInt();
			input.nextLine();

			System.out.printf("\nNota para a atividade: ");
			double activityGrade = input.nextDouble();
			input.nextLine();

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

	public void report(Scanner input) {
		printItemOptions("Visao geral do periodo academico\n" + "Estatisticas dos docentes\n"
				+ "Estatisticas dos estudantes\n" + "Estatisticas das disciplinas de um docente\n");
		int userDecision = input.nextInt();
		input.nextLine();
		switch (userDecision) {

		case 1:
			listPeriods();
			System.out.println("Digite o periodo cadastrado no formato ANO/SEMESTRE (ex: 2019/1) ");
			String periodReference = input.nextLine();
			report.periodsReport(periods.get(periodReference));
			System.out.println("\n precione qualquer tecla para continuar: ");
			input.nextInt();
			break;

		case 2:
			System.out.println("\n\n\tEstatisticas dos docentes");
			System.out.println("\nPROFESSORES: \n");

			
			if (teachers.size() > 0) {
				for(Map.Entry<String, Teacher> teacher : teachers.entrySet()) {
					teacher.getValue().findAssociatedDisciplines(disciplines);
					report.teachersReport(teacher.getValue());
				}
			}
			System.out.println("\n precione qualquer tecla para continuar: ");
			input.nextInt();
			break;

		case 3:
			System.out.println("\n\n\tEstatisticas dos estudantes");
			System.out.println("\nEstudantes: \n");

			
			if (students.size() > 0) {
				for(Entry<Integer, Student> student : students.entrySet()) {
					student.getValue().findAssociatedDisciplines(disciplines);
					report.studentsReport(student.getValue());
				}
			}
			System.out.println("\n precione qualquer tecla para continuar: ");
			input.nextInt();
			break;

		case 4:
			
			listTeachers();
			System.out.println("Digite o login institucional do docente: ");
			String teacherReference = input.nextLine();
			Teacher teacher = teachers.get(teacherReference);
			if(teacher!=null) {
				
				report.teachersDisciplinesReport(teacher);
				System.out.println("\n precione qualquer tecla para continuar: ");
				input.nextInt();
				
			}
			
			break;

		}

	}

}


