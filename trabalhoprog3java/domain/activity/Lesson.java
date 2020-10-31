package trabalhoprog3java.domain.activity;

import java.util.Date;

import trabalhoprog3java.domain.Discipline;

public class Lesson extends Activity {
	
	private Date date;
	private String time;
	private int maxPeopleQuantity;
	private double  workload;
	
	public Lesson(String name, Discipline discipline, Date date, String time) {
		super(name, discipline,false,true);
		this.date = date;
		this.time = time;
	}



	public Date getDate() {
		return date;
	}

	

	public double getWorkload() {
		return workload;
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
