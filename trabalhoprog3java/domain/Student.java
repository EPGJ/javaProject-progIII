package trabalhoprog3java.domain;
import java.util.Map;

public class Student extends Person {


	private int registrationCode;
    
    
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

    public double calculateAverageEnrolledDisciplinesByPeriod() {
    	return ((double)super.getAssociatedDisciplines().size())/((double)super.getNumberOfAssociatedPeriods());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
