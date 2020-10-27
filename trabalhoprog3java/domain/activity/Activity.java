package trabalhoprog3java.domain.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import trabalhoprog3java.domain.Discipline;


abstract public class Activity implements Serializable {

	private String name;
    private boolean synchronous;
    private Discipline discipline;
    private int activityNumber;
    private List<ActivityRating> studentsRating = new ArrayList<>();
    private double workLoad;
    private boolean isAvaliative;

    public Activity(String name, Discipline discipline,boolean isAvaliative, boolean synchronous,double workLoad) {
    	this.name = name;
        this.synchronous = synchronous;
        this.discipline = discipline;
    	this.workLoad = workLoad;
    	this.isAvaliative= isAvaliative;
    }

    public Activity(String name, Discipline discipline,boolean isAvaliative, boolean synchronous) {
    	this(name,discipline,isAvaliative,synchronous,2.0);
    }

    public Activity(String name, Discipline discipline,boolean isAvaliative) {
        this(name, discipline,isAvaliative, false,2.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSynchronous() {
        return synchronous;
    }

    public void setSynchronous(boolean synchronous) {
        this.synchronous = synchronous;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline dicipline) {
        this.discipline = dicipline;
    }

    public int getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(int activityNumber) {
        this.activityNumber = activityNumber;
    }

    public String getActivityData() {
    	String activityData = "\nNome: "+this.name+ "\nDisciplina: "+this.discipline.getName() 
    							+ "\nEh sincrona?: "+this.isSynchronous() ;

    	if(this.studentsRating.size()>0) {
    		activityData += "\nNota de avaliacao da atividade dada pelos estudantes: ";
    		for(int i = 0; i < studentsRating.size();i++) {
    			activityData += "    " +studentsRating.get(i).getGrade();
    		}
    	}
    	
    	return activityData;
    }
    public List<ActivityRating> getstudentsRating() {
        return this.studentsRating;
    }

    public void setSudentsEvaluation(ActivityRating studentEvaluation) {
        this.studentsRating.add(studentEvaluation);
    }
    
    public double getAverageStudentsRating() {
		double numberEvaluations= studentsRating.size();
		double sumEvaluations=0;
		
		for(ActivityRating activityRating: studentsRating) {
			sumEvaluations+=activityRating.getGrade();
		}
		
		return (sumEvaluations/numberEvaluations);
	}

	public double getWorkLoad() {
		return workLoad;
	}

	public boolean isAvaliative() {
		return isAvaliative;
	}

 
}
