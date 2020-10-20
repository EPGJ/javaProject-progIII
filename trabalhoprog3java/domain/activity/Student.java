package trabalhoprog3java.domain.activity;
import java.util.Map;

import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.Person;

public class Student extends Person {


	private int registrationCode;
    
    public Student(){
    }
    
    public Student(int registrationCode, String fullName){
        super(fullName);
        this.registrationCode = registrationCode; 
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
    @Override
    public void findAssociatedDisciplines(Map<String, Discipline> disciplines) {
		for (Map.Entry<String, Discipline> discipline : disciplines.entrySet()) {
			if( discipline.getValue().isStudentInDiscipline(this) ) {
				super.getAssociatedDisciplines().add(discipline.getValue());
			}
			
		}
	}
    public double calculateAverageEnrolledDisciplinesByPeriod() {
    	return ((double)super.getAssociatedDisciplines().size())/((double)super.getNumberOfAssociatedPeriods());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
