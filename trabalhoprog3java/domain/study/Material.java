package trabalhoprog3java.domain.study;
import java.io.Serializable;

public class Material implements Serializable{
	private String name;
	private String link;
	
	public Material(String name, String link) {
		this.name = name;
		this.link = link;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

}
