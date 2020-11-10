package trabalhoprog3java;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import trabalhoprog3java.controller.MenuCsv;
import trabalhoprog3java.domain.Period;

public class MainCsv implements Serializable {
	public static void main(String[] args) throws Exception {
		Locale.setDefault(new Locale("pt", "BR"));
		MenuCsv menu = new MenuCsv();
		try {
			menu.setFilesList(args);
			if (menu.readOnly) {
				menu.readFiles();
				menu.serialize();	
			} else {
				if (menu.writeOnly) {
					menu = menu.deserialize();
				}else {
					menu.readFiles();
				}
				menu.generateReports();
			}

		}catch (IOException | ClassNotFoundException e) {
			System.out.println("Erro de I/O.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}

	}
}
