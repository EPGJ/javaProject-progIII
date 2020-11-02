package trabalhoprog3java.domain.activity.evaluative;

import java.util.Date;

import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.activity.Activity;

abstract public class EvaluativeActivity extends Activity  {

	private Date date;
	
	public EvaluativeActivity(String name, Discipline discipline,Date date, double workLoad) { // constutor para trabalho
		super(name, discipline, true,false, workLoad);
		this.setDate(date);
	}
	
	public EvaluativeActivity(String name, Discipline discipline, Date date ) {//constutor para prova
		super(name, discipline,true,true);
		this.setDate(date);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
