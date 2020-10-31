package trabalhoprog3java.domain.activity;
import java.util.Date;

import trabalhoprog3java.domain.Discipline;


public class Test extends Activity {
	private Date date;
	private String time;
	private String testContent;
	
	public Test(String name, Discipline discipline, Date date, String time, String testContent) {
		super(name, discipline,true,true);
		this.date = date;
		this.time = time;
		this.testContent = testContent;
	}

	public Date getDate() {
		return date;
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
