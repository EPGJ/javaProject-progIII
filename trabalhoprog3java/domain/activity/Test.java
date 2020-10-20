package trabalhoprog3java.domain.activity;
import java.util.Date;


public class Test extends Activity {
	private String date;
	private String time;
	private String testContent;
	
	public Test(String name, String discipline, String date, String time, String testContent) {
		super(name, discipline,true,true);
		this.date = date;
		this.time = time;
		this.testContent = testContent;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
