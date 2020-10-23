package trabalhoprog3java.domain.activity;
import java.util.Date;

import trabalhoprog3java.domain.Discipline;


public class Work extends Activity {
		
	private String deadline;
	private int maxNumber;

	
	public Work(String name, Discipline discipline,String deadline, int maxNumber, double workLoad) {
		super(name, discipline, true,false, workLoad);
		this.maxNumber = maxNumber;
		this.deadline = deadline;
		
	}


	public String getDeadline() {
		return deadline;
	}


	public int getMaxNumber() {
		return maxNumber;
	}


}
