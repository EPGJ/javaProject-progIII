package trabalhoprog3java;

import trabalhoprog3java.controller.Menu;
import trabalhoprog3java.controller.ReadData;
import trabalhoprog3java.controller.Utils;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class Main implements Serializable{
    public static void main(String[] args) throws Exception {
		Locale.setDefault(new Locale("pt", "BR"));
        int option = 0;
        ReadData readData = new ReadData();
        Menu menu = new Menu(readData);
        Utils util = new Utils(readData);
        util.cleanConsole();
        



		
//		Date data1,data2;
//        String line = "26/11/2020";
//        String line2 = "26/12/2020";
//        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy"); 
//        data1 = f.parse(line);
//        data2 = f.parse(line2);
//        System.out.println(data1);
//        
//        if(data1.before(data2)) {
//        	System.out.println("eh antes");
//        }
//        else {
//        	System.out.println("eh depois");
//        }

		
        do {
            menu.printMenuOptions();
            option = readData.readUserDecision(11);  // o usuario possui onze opcoes de escolha


            switch (option) {
       
                case 1:
                	menu.periodRegister();
                	break;
      
                case 2:
                    menu.teacherRegister();
                    break;
            
                case 3:
                    menu.disciplineRegister();
                    break;

                case 4:
                    menu.studentRegister();
                    break;

                case 5:
                    menu.enrollStudent();
                    break;

                case 6:
                	menu.activityRegister();
                    break;

                case 7:
                	menu.activityRating();
                    break;
                
                case 8:
                    menu.report();
                    break;
                case 9:
                    util.serialize(menu);
                    break;
                case 10:
                    menu  = util.deserialize();
                    break;
            
                default:
                    break;
            }
//            util.cleanConsole();
        } while (option != 11);
        readData.closeInput();
    }
}
