package trabalhoprog3java.controller.util;

import java.util.Comparator;

import trabalhoprog3java.domain.Discipline;



public class SortTeachersDisciplines implements Comparator<Discipline>{

	@Override
	public int compare(Discipline dc1, Discipline dc2) {
		int periodCompare = dc1.getPeriod().compareTo(dc2.getPeriod());
		if(periodCompare ==0) {
			return dc1.getCode().compareTo(dc2.getCode());
		}else {
			return periodCompare;
		}
	}


}
