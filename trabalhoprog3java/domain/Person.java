package trabalhoprog3java.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person implements Serializable{


	private String fullName;
    private List<Discipline> associatedDisciplines;


    public Person(String fullName){
        this.fullName = fullName;
        this.associatedDisciplines = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public List<Discipline> getAssociatedDisciplines(){
		return this.associatedDisciplines;
	}
    
    public void setAssociatedDiscipline(Discipline discipline) {
    	this.associatedDisciplines.add(discipline);
    }
    
    
    
    public int getNumberOfAssociatedPeriods() {
    	Map<String,Period> periods = new HashMap<>();
    	Period period;
    	for(Discipline discipline: associatedDisciplines) {
    		period = discipline.getPeriod();
    		if(periods.get(period.getPeriodReference()) == null) {
    			periods.put(period.getPeriodReference(),period);
    		}
    		
    	}
    	return periods.size();
    }
    
 
    
    
}
