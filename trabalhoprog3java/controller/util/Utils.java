package trabalhoprog3java.controller.util;

import java.util.Map;
import java.util.Scanner;

import trabalhoprog3java.controller.MenuCsv;
//import trabalhoprog3java.controller.Menu;
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

	

	public void serialize(MenuCsv menu) {
		try {

			FileOutputStream fileOut = new FileOutputStream("./dados.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(menu);
			out.close();
			fileOut.close();
			System.out.println("Objeto gravado com sucesso!");
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	public MenuCsv deserialize() {
		MenuCsv menu = null;
		try {
			FileInputStream fileIn = new FileInputStream("./dados.dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);

			menu = (MenuCsv) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();

		} catch (ClassNotFoundException c) {
			System.out.println("Menu nao encontrado");
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
