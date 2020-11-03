package trabalhoprog3java;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import trabalhoprog3java.controller.MenuCsv;
import trabalhoprog3java.domain.Period;


public class MainCsv implements Serializable{
    public static void main(String[] args) throws Exception {
    	MenuCsv menu = new MenuCsv();
    	try {
    		menu.setFilesList(args);
    	}catch(ArrayIndexOutOfBoundsException e) {
    		System.out.println("Erro ao ler os parametros de entrada");
    	}
    	catch(Exception e) {
    		System.out.println("Erro de I/O");
    	}

    	menu.fileList.forEach((key, file) ->
    	System.out.println(file)
    	);
    	
    	

    }
}




























