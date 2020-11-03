package trabalhoprog3java.controller.util;

import java.util.Map;
import java.util.Scanner;

import trabalhoprog3java.controller.Menu;
import trabalhoprog3java.controller.ReadData;
import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.Student;
import trabalhoprog3java.domain.activity.Activity;
import trabalhoprog3java.domain.activity.ActivityRating;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Utils implements Serializable {
	private ReadData readData;
		
	public Utils(ReadData readData) {
		this.readData = readData;
	}
	
	public Discipline findDiscipline(String code, String period, Map<String, Discipline> disciplines) {
		return disciplines.get(code + "-" + period);
	}

	public Student findStudent(int code, Map<Integer, Student> students) {
		return students.get(code);
	}

	public void cleanConsole() {
		System.out.println("\033[H\033[2J");
	}

	public void serialize(Menu menu) {
		try {

			System.out.printf("Digite o nome do arquivo: ");
			String fileName = readData.readString();

			FileOutputStream fileOut = new FileOutputStream("./" + fileName + ".dat");

			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(menu);
			out.close();
			fileOut.close();
			System.out.println("Objeto gravado com sucesso!");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	public Menu deserialize() {
		Menu menu = null;
		try {

			System.out.printf("Digite o nome do arquivo: ");
			String fileName = readData.readString();

			FileInputStream fileIn = new FileInputStream("./" + fileName + ".dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);

			menu = (Menu) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();

		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();

		}
		return menu;
	}

	public boolean isEnrolled(Discipline discipline, Student student) {

		for (Student enrolledStudent : discipline.getEnrolledStudents()) {
			if (enrolledStudent == student) {
				return true;
			}
		}
		return false;
	}

	public void pressAnyKeyToContinue() {
		System.out.println("Pressione Enter para continuar...");
		try {
			System.in.read();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public boolean studentAlreadyEvaluatedAtivity(Student student, Activity activity) {
		
		for(ActivityRating activityRating: activity.getstudentsRating()) {
			if(activityRating.getStudent() == student) {
				return true;
			}
		}		
		return false;
	}
}
