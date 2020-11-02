package trabalhoprog3java.domain.activity.evaluative;
import java.util.Date;

import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.activity.Activity;


public class Test extends EvaluativeActivity {
	private String time;
	private String testContent;
	
	public Test(String name, Discipline discipline, Date date, String time, String testContent) {
		super(name, discipline,date); //eh  avaliativa e sincrona 
		this.time = time;
		this.testContent = testContent;
	}


	public String getTestContent() {
		return testContent;
	}

	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}

	public String getTime() {
		return time;
	}

}
