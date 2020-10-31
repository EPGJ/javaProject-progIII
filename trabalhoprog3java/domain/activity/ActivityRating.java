package trabalhoprog3java.domain.activity;

import java.io.Serializable;

import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.Student;

public class ActivityRating implements Serializable{
    private Student student;
    private Discipline discipline;
    private double grade;

    public ActivityRating(Student student,Discipline discipline,double grade){
        this.setDiscipline(discipline);
        this.setStudent(student);
        this.setGrade(grade);
    }

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	
	
}
