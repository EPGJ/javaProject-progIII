package trabalhoprog3java;

import java.util.Scanner;

import trabalhoprog3java.controller.Menu;
import trabalhoprog3java.controller.Utils;
import trabalhoprog3java.exception.NotCharException;

import java.util.InputMismatchException;
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
            input.nextLine();

            switch (option) {
       
                case 1:
                	
                	try {
                    	menu.periodRegister(input);
                	}catch(InputMismatchException e) {
                		System.out.println(e);
                		input.nextLine();
                	}catch(NotCharException e) {
                		System.out.println(e);
                	}
                	
                	break;
      
                case 2:
                    menu.teacherRegister(input);
                    break;
            
                case 3:
                    menu.disciplineRegister(input);
                    break;

                case 4:
                    menu.studentRegister(input);
                    break;

                case 5:
                    menu.enrollStudent(input);
                    break;

                case 6:
                	menu.activityRegister(input);
                    break;

                case 7:
                	menu.activityRating(input);
                    break;
                
                case 8:
                    menu.report(input);
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
            util.cleanConsole();
        } while (option != 11);
        input.close();
    }
}
