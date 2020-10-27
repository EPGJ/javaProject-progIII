package trabalhoprog3java;

import java.util.Scanner;

import trabalhoprog3java.controller.Menu;
import trabalhoprog3java.controller.Utils;

import java.util.Locale;



public class Main {
    public static void main(String[] args) throws Exception {
		Locale.setDefault(new Locale("pt", "BR"));
        int option = 0;
        Scanner input = new Scanner(System.in);
        Utils util = new Utils();
        util.cleanConsole();
        Menu menu = new Menu();

        do {
            menu.printMenuOptions();
            option = input.nextInt();
            util.clearBuffer(input);

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
                    util.serialize(menu, input);
                    break;
                case 10:
                    menu  = util.deserialize(input);
                    break;
            
                default:
                    break;
            }
//            util.cleanConsole();
        } while (option != 11);
        input.close();
    }
}
