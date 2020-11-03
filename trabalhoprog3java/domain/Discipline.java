package trabalhoprog3java.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import trabalhoprog3java.domain.activity.Activity;





public class Discipline implements Serializable, Comparable<Discipline>{

	private String code;
    private String name;
    private Period period;
    private Teacher responsableTeacher;
    private List<Activity> activities;
    private List<Student> students;

  

    public  Discipline(String code, String name, Period period, Teacher responsableTeacher){
        this.code = code;
        this.name = name;
        this.period = period;
        this.responsableTeacher = responsableTeacher;
        this.activities = new ArrayList<>();
        this.students = new ArrayList<>();
        
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Teacher getResponsableTeacher() {
        return responsableTeacher;
    }

    public void setResponsableTeacher(Teacher responsableTeacher) {
        this.responsableTeacher = responsableTeacher;
    }

    public List<Activity> getActivities() {
        return this.activities;
    }

    public void setActivity(Activity newActivity) {
        this.activities.add(newActivity);
    }
 
    public void enrollStudent(Student student){
        this.students.add(student);
    }
    public List<Student> getEnrolledStudents(){
        return this.students;
    }
    public String getDisciplineReference(){
        return this.code+"-"+this.period.getPeriodReference();
    }
    public String getDisciplineData(){
    	
    	String disciplineData = "\nCodigo: "+this.code+"\nNome: "+this.name+"\nPeriodo: "
    							+this.period.getPeriodReference()+"\nProfessor responsavel: "+this.responsableTeacher.getFullName();
    	if(students.size()>0 ) {
    		disciplineData += "\nEstudantes matriculados: ";
    		for(int i = 0; i < students.size();i++) {
    			disciplineData += "\n\tMatricula do estudante: "+students.get(i).getStudentReference();
    		}	
    	}
    	
    	if(activities.size()>0) {
    		disciplineData += "\nAtividades cadastradas: ";
    		for(int i = 0; i < activities.size();i++) {
    			disciplineData += "\n\tNome da atividade: "+activities.get(i).getName() + "\n\tNumero da atividade: "+activities.get(i).getActivityNumber();
    		}
    	}
    	return disciplineData;
    	
    }
    public boolean isStudentInDiscipline(Student student) {
    	for(Student elem: students) {
    		if(elem == student) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public double getTotalSynchronousActivities() {
		int totalSynchronousActivities = 0;
		for (Activity activity : activities) {
			if (activity.isSynchronous()) {
				totalSynchronousActivities++;
			}
		}
		return totalSynchronousActivities;

	}
    
    public double calculatePercentageSynchronousActivities() {
    	double quantitySyncActivities = getTotalSynchronousActivities();
    	double quantityAtivities = activities.size();
    	
    	if(quantityAtivities==0) {
    		return -1;
    	}
    	else {    		
    		return ((quantitySyncActivities/quantityAtivities)*100.0);
    	}
    }
    
    public double getWorkLoad() {
    	double disciplineWorkLoad =0;
    	for(Activity activity: activities) {
    		disciplineWorkLoad+=activity.getWorkLoad();
    	}
    	return disciplineWorkLoad;
    }

	@Override
	public int compareTo(Discipline discipline) {
		return this.name.compareTo(discipline.name);
	}

}
