package trabalhoprog3java.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Period implements Serializable {
    private int year;
    private char semester;
    private List<Discipline> disciplines = new ArrayList<>();

    
    public Period(int year, char semester ){
        this.semester = semester;
        this.year = year;
    }

    public char getSemester() {
        return semester;
    }
    public void setSemester(char semester) {
        this.semester = semester;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getPeriodReference() {
        return Integer.toString(year) +"/" +semester;
    }	
    public String getPeriodData(){
    	return "\nAno: "+this.year +"\nSemestre: "+this.semester;
    }

	public List<Discipline> getDisciplines() {
		return disciplines;
	}

	public void setDiscipline(Discipline discipline) {
		this.disciplines.add(discipline);
	}
}
