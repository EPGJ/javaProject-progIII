package trabalhoprog3java.domain.activity;

import java.util.Date;

public class Lesson extends Activity {
	
	private String date;
	private String time;
	private int maxPeopleQuantity;
	private double  workload;
	
	public Lesson(String name, String discipline, String date, String time) {
		super(name, discipline,false,true);
		this.date = date;
		this.time = time;
	}



	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getWorkload() {
		return workload;
	}

	public void setWorkload(double workload) {
		this.workload = workload;
	}

	public int getMaxPeopleQuantity() {
		return maxPeopleQuantity;
	}

	public void setMaxPeopleQuantity(int maxPeopleQuantity) {
		this.maxPeopleQuantity = maxPeopleQuantity;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
