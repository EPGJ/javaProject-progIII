package trabalhoprog3java.domain.study;
import java.util.ArrayList;
import java.util.List;

import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.activity.Activity;


public class Study extends Activity{
	
	private List<Material> materials = new ArrayList<>();

	public Study(String name, Discipline discipline,  List<Material> materials) {
		super(name, discipline,false,false);
		this.materials =  materials;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Material> materials) {
		this.materials = materials;
	}

}
