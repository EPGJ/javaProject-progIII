package trabalhoprog3java.domain.activity.evaluative;
import java.util.Date;

import trabalhoprog3java.domain.Discipline;


public class Work extends EvaluativeActivity {
		
	private int maxNumber;

	
	public Work(String name, Discipline discipline,Date deadline, int maxNumber, double workLoad) {
		super(name, discipline, deadline,workLoad);
		this.maxNumber = maxNumber;		
	}

	public int getMaxNumber() {
		return maxNumber;
	}


}
