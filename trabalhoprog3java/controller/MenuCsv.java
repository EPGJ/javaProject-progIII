package trabalhoprog3java.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.io.File;
import java.io.PrintWriter;

import trabalhoprog3java.controller.util.SortTeachersDisciplines;
import trabalhoprog3java.controller.util.Utils;
import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.Period;
import trabalhoprog3java.domain.Student;
import trabalhoprog3java.domain.Teacher;
import trabalhoprog3java.domain.activity.Activity;
import trabalhoprog3java.domain.activity.ActivityRating;
import trabalhoprog3java.domain.activity.Lesson;
import trabalhoprog3java.domain.activity.evaluative.Test;
import trabalhoprog3java.domain.activity.evaluative.Work;
import trabalhoprog3java.domain.activity.study.Material;
import trabalhoprog3java.domain.activity.study.Study;
import trabalhoprog3java.exception.ActivityAlreadyEvaluatedException;
import trabalhoprog3java.exception.InvalidReferenceException;
import trabalhoprog3java.exception.NotAnOptionException;
import trabalhoprog3java.exception.NotCharException;
import trabalhoprog3java.exception.ReferenceAlredyExistsException;

public class MenuCsv implements Serializable {
	private static final long serialVersionUID = 1L;
	Map<String, Period> periods;
	Map<String, Discipline> disciplines;
	Map<Long, Student> students;
	public Map<String, Teacher> teachers;
	List<Activity> activities;
	Utils util;
	ReportCsv report;
	CsvReader input;
	public boolean readOnly;
	public boolean writeOnly;
	public Map<String, String> fileList;
	DataValidation validateData;

	
	public MenuCsv() throws IOException {

		this.report = new ReportCsv();
		this.periods = new HashMap<>();
		this.disciplines = new HashMap<>();
		this.students = new HashMap<>();
		this.teachers = new HashMap<>();
		this.activities = new ArrayList<>();
		this.fileList = new HashMap<>();
		this.validateData = new DataValidation();
		this.util = new Utils();
		this.readOnly = false;
		this.writeOnly = false;
	}

	public void setFilesList(String[] args) {
		for (int i = 0; i < args.length; i++) {

			if (args[i].equals("-p"))
				this.fileList.put("periodsFile", args[i + 1]);
			if (args[i].equals("-d"))
				this.fileList.put("teachersFile", args[i + 1]);
			if (args[i].equals("-o"))
				this.fileList.put("disciplinesFile", args[i + 1]);
			if (args[i].equals("-e"))
				this.fileList.put("studentsFile", args[i + 1]);
			if (args[i].equals("-m"))
				this.fileList.put("enrollmentsFile", args[i + 1]);
			if (args[i].equals("-a"))
				this.fileList.put("activitiesFile", args[i + 1]);
			if (args[i].equals("-n"))
				this.fileList.put("activitiesRatingFile", args[i + 1]);
			if (args[i].equals("--read-only")) {
				this.readOnly = true;
				System.out.println("read only!");
			}
			if (args[i].equals("--write-only")) {
				this.writeOnly = true;
				System.out.println("write only!");
			}
		}
	}

	public void readFiles() throws IOException, Exception {
		String path = "../Testes/01/in/";

		periodsRegister();
		teachersRegister();
		disciplinesRegister();
		studentsRegister();
		enrollStudents();
		activitiesRegister();
		activitiesRating();

	}

	public void generateReports() throws IOException {
		generatePeriodsReport();
		generateTeachesReport();
		generateStudentsReport();
		generateTeachersReport();
	}

	public void periodsRegister() throws NumberFormatException, NotAnOptionException, NotCharException,
			ReferenceAlredyExistsException, IOException {
		this.input = new CsvReader(this.fileList.get("periodsFile"));
		input.nextLine(); // limpa cabecalho
		String[] periodData = input.nextLine();
		do {

			int year = validateData.validateYear(periodData[0]);
			char semester = validateData.validateChar(periodData[1]);

			Period period = new Period(year, semester);
			if (periods.get(period.getPeriodReference()) != null) {
				throw new ReferenceAlredyExistsException(period.getPeriodReference());
			}
			periods.put(period.getPeriodReference(), period);
			periodData = input.nextLine();
		} while (periodData != null);
	}

	public void teachersRegister() throws IOException, InvalidReferenceException, ReferenceAlredyExistsException {
		this.input = new CsvReader(this.fileList.get("teachersFile"));
		input.nextLine();
		String[] teacherData = input.nextLine();
		do {

			String login = validateData.validateLogin(teacherData[0]);
			String fullName = teacherData[1];
			Teacher teacher;
			if (teacherData[2] != null) {
				String webPage = teacherData[2];
				teacher = new Teacher(login, fullName, webPage);
			} else {
				teacher = new Teacher(login, fullName);
			}

			if (teachers.get(teacher.getTeacherReference()) != null) {
				throw new ReferenceAlredyExistsException(teacher.getTeacherReference());
			}
			teachers.put(teacher.getTeacherReference(), teacher);
			teacherData = input.nextLine();
		} while (teacherData != null);

	}

	public void disciplinesRegister() throws IOException, InvalidReferenceException, ReferenceAlredyExistsException {
		this.input = new CsvReader(this.fileList.get("disciplinesFile"));
		input.nextLine();
		String[] disciplineData = input.nextLine();
		do {
			String periodReference = disciplineData[0];
			String code = disciplineData[1];
			String name = disciplineData[2];
			String responsableTeacher = validateData.validateLogin(disciplineData[3]);
			Teacher teacher = teachers.get(responsableTeacher);
			if (teacher == null) {
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
			teacher.setAssociatedDiscipline(discipline);
			disciplines.put(discipline.getDisciplineReference(), discipline);
			period.setDiscipline(discipline);

			disciplineData = input.nextLine();
		} while (disciplineData != null);
	}

	public void studentsRegister() throws IOException, ReferenceAlredyExistsException, NumberFormatException {
		this.input = new CsvReader(this.fileList.get("studentsFile"));
		input.nextLine();
		String[] studentsData = input.nextLine();
		do {

			long code = validateData.validateNumber(studentsData[0]);
			String fullName = studentsData[1];
			Student student = new Student(code, fullName);
			if (students.get(student.getStudentReference()) != null) {
				throw new ReferenceAlredyExistsException(String.valueOf(code));
			}
			students.put(student.getStudentReference(), student);

			studentsData = input.nextLine();
		} while (studentsData != null);
	}

	public void enrollStudents() throws IOException, InvalidReferenceException, ReferenceAlredyExistsException {
		this.input = new CsvReader(this.fileList.get("enrollmentsFile"));
		input.nextLine();
		String[] enrollmentsData = input.nextLine();
		do {
			String disciplineReference = enrollmentsData[0];
			long studentReference = validateData.validateNumber(enrollmentsData[1]);
			Discipline discipline = disciplines.get(disciplineReference);
			if (discipline == null) {
				throw new InvalidReferenceException(disciplineReference);
			}

			Student student = students.get(studentReference);
			if (student == null) {
				throw new InvalidReferenceException(String.valueOf(studentReference));
			}

			if (util.isEnrolled(discipline, student)) {
				throw new ReferenceAlredyExistsException(Long.toString(student.getStudentReference()));
			}
			student.setAssociatedDiscipline(discipline);
			discipline.enrollStudent(student);
			enrollmentsData = input.nextLine();
		} while (enrollmentsData != null);
	}

	public void activitiesRegister() throws IOException, NotCharException, InvalidReferenceException, ParseException {
		this.input = new CsvReader(this.fileList.get("activitiesFile"));
		input.nextLine();
		String[] activitiesData = input.nextLine();
		do {
			String disciplineReference = validateData.validateDisciplineReference(activitiesData[0]);
			Discipline discipline = disciplines.get(disciplineReference);
			if (discipline == null) {
				throw new InvalidReferenceException(disciplineReference);
			}

			String name = activitiesData[1];
			char type = validateData.validateChar(activitiesData[2]);

			switch (type) {
			case 'A':

				Date date = validateData.validateDate(activitiesData[3]);
				String time = validateData.validateTime(activitiesData[4]);

				Lesson lesson = new Lesson(name, discipline, date, time);
				discipline.setActivity(lesson);
				lesson.setActivityNumber(discipline.getActivities().size());
				this.activities.add(lesson);

				break;

			case 'E':

				List<Material> materials = validateData.validateMaterials(activitiesData[5]);
				Study study = new Study(name, discipline, materials);

				discipline.setActivity(study);
				study.setActivityNumber(discipline.getActivities().size());
				activities.add(study);

				break;

			case 'P':
				date = validateData.validateDate(activitiesData[3]);
				time = validateData.validateTime(activitiesData[4]);
				String content = activitiesData[5];

				Test test = new Test(name, discipline, date, time, content);

				discipline.setActivity(test);
				for (Student student : discipline.getEnrolledStudents()) {
					student.setAvaliativeActivities(test);
				}

				test.setActivityNumber(discipline.getActivities().size());
				activities.add(test);

				break;

			case 'T':
				date = validateData.validateDate(activitiesData[3]);
				int maxNumber = (int) validateData.validateNumber(activitiesData[6]);
				double workload = (double) validateData.validateDouble(activitiesData[7]);
				Work work = new Work(name, discipline, date, maxNumber, workload);

				discipline.setActivity(work);
				for (Student student : discipline.getEnrolledStudents()) {
					student.setAvaliativeActivities(work);
				}

				work.setActivityNumber(discipline.getActivities().size());

				activities.add(work);

				break;

			default:
			}

			activitiesData = input.nextLine();
		} while (activitiesData != null);
	}

	public void activitiesRating() throws IOException, InvalidReferenceException, ActivityAlreadyEvaluatedException {
		this.input = new CsvReader(this.fileList.get("activitiesRatingFile"));
		input.nextLine();
		String[] activitiesRatingData = input.nextLine();
		do {
			String disciplineReference = validateData.validateDisciplineReference(activitiesRatingData[0]);
			long studentReference = validateData.validateNumber(activitiesRatingData[1]);
			int activityNumber = (int) validateData.validateNumber(activitiesRatingData[2]);
			double activityGrade = validateData.validateDouble(activitiesRatingData[3]);

			Discipline discipline = disciplines.get(disciplineReference);
			if (discipline == null) {
				throw new InvalidReferenceException(disciplineReference);
			}

			Student student = students.get(studentReference);
			if (student == null) {
				throw new InvalidReferenceException(String.valueOf(studentReference));
			}

			Activity activity = discipline.getActivities().get(activityNumber - 1);
			if (activity == null) {
				throw new InvalidReferenceException(discipline.getDisciplineReference() + "-" + activityNumber);
			}

			if (util.studentAlreadyEvaluatedAtivity(student, activity)) {
				throw new ActivityAlreadyEvaluatedException(student.getStudentReference(), activityNumber,
						discipline.getDisciplineReference());
			} else {
				ActivityRating activityRating = new ActivityRating(student, discipline, activityGrade);
				student.setEvaluation(activityRating);
				activity.setSudentEvaluation(activityRating);
			}
			activitiesRatingData = input.nextLine();

		} while (activitiesRatingData != null);
	}

	public void generatePeriodsReport() throws IOException {
		List<Period> periodsList = new ArrayList<>(periods.values());
		Collections.sort(periodsList);
		report.periodsReport(periodsList);
	}

	public void generateTeachesReport() throws IOException {
		List<Teacher> teachersList = new ArrayList<>(teachers.values());
		Collections.sort(teachersList);
		report.teachersReport(teachersList);
	}

	public void generateStudentsReport() throws IOException {
		List<Student> studentList = new ArrayList<>(students.values());
		Collections.sort(studentList);
		report.studentsReport(studentList);

	}

	public void generateTeachersReport() throws IOException {
		List<Discipline> disciplineList = new ArrayList<>(disciplines.values());
		Collections.sort(disciplineList, new SortTeachersDisciplines());
		report.disciplinesReport(disciplineList);
	}
	public void serialize() {
		this.util.serialize(this);
	}
	public MenuCsv deserialize() {
		return this.util.deserialize();
	}

}

//if (teachers.size() > 0) {
//System.out.println("\n\nDocentes cadastrados: ");
//teachers.forEach((key, teacher) -> System.out.println(teacher.getTeacherData()));
//
//}
//if (periods.size() > 0) {
//System.out.println("\n\nPeriodos registrados: ");
//periods.forEach((key, period) -> System.out.println(period.getPeriodData()));
//}
//if (disciplines.size() > 0) {
//System.out.println("\n\nDisciplinas cadastradas: ");
//
//disciplines.forEach((key, discipline) -> System.out.println(discipline.getDisciplineData()));
//}
//if (students.size() > 0) {
//System.out.println("\n\nEstudantes cadastrados: ");
//students.forEach((key, student) -> System.out.println(student.getStudentData()));
//
//}
//if (!activities.isEmpty()) {
//System.out.println("\n\nAtividades cadastradas: ");
//activities.forEach(activity -> System.out.println(activity.getActivityData()));
//}
