package trabalhoprog3java.domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import trabalhoprog3java.domain.activity.evaluative.EvaluativeActivity;

public class Student extends Person implements Comparable<Student>{


	private int registrationCode;
	private List<EvaluativeActivity> avaliativeActivities;
    
    
    public Student(int registrationCode, String fullName){
        super(fullName);
        this.registrationCode = registrationCode; 
        this.avaliativeActivities = new ArrayList<>();
    }

    public int getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(int registrationCode) {
        this.registrationCode = registrationCode;
    }
  
    public int getStudentReference(){
        return this.registrationCode;
    }
    public String getStudentData(){
    	return "\nMatricula: " +this.registrationCode+"\nNome Completo: " +this.getFullName();
    }

    public double calculateAverageEnrolledDisciplinesByPeriod() {
    	double disciplineQuantity = super.getAssociatedDisciplines().size();
    	double periodQuantity = super.getNumberOfAssociatedPeriods();
    	if(periodQuantity==0) {
    		return 0;    		
    	}else {
    		return disciplineQuantity/periodQuantity;
    	}
    	
    	
    }
    
    public int getNumberDoneActivities() {
    	Date today = new Date();
    	int count =0;
//    	System.out.println("AA1");
    	for(EvaluativeActivity activity: avaliativeActivities) {
//    		System.out.println("AA2"+ activity.getName());
    		if(today.after(activity.getDate())){
    			count++;
    		}
    		
    	}
    	return count;
    }

	@Override
	public int compareTo(Student student) {
//		System.out.println("passou1");
		int compareDoneActivities = student.getNumberDoneActivities() - this.getNumberDoneActivities();
//		System.out.println("passou2");
		if(compareDoneActivities == 0) {
//			System.out.println("passou3");
			return super.getFullName().compareTo(student.getFullName());
		}
		else {
//			System.out.println("passou4");
			return student.getNumberDoneActivities() - this.getNumberDoneActivities();
		}		
	}
	
	public double calculateAverageNumberDoneActivitiesPerDiscipline() {
		double numberDoneActivities = this.getNumberDoneActivities();
		double numberDisciplines = super.getAssociatedDisciplines().size();
		
		if(numberDisciplines == 0) {
			return 0;
		}
		else {
			return numberDoneActivities/numberDisciplines;
		}
	}
	

	public List<EvaluativeActivity> getAvaliativeActivities() {
		return avaliativeActivities;
	}

	public void setAvaliativeActivities(EvaluativeActivity activity) {
		this.avaliativeActivities.add(activity);
	}
}
