package trabalhoprog3java;
import java.util.Map;
import java.util.Scanner;

import trabalhoprog3java.domain.Discipline;
import trabalhoprog3java.domain.activity.Student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Utils implements Serializable{
    
    public  Discipline findDiscipline(String code, String period, Map<String,Discipline> disciplines){
        return disciplines.get(code+"-"+period);
    }
    public  Student findStudent(int code, Map<Integer,Student> students){
        return students.get(code);
    }
    public void cleanConsole()  {
             System.out.println("\033[H\033[2J");
    }
    public void serialize(Menu menu,Scanner input) {
    	try
        {
    		
    	  System.out.printf("Digite o nome do arquivo: ");
    	  String fileName = input.next();
    	  
          FileOutputStream fileOut =
          new FileOutputStream("./"+fileName+".dat");

          ObjectOutputStream out = new ObjectOutputStream(fileOut);

          out.writeObject(menu);
          out.close();
          fileOut.close();
          System.out.println("Objeto gravado com sucesso!");
        }catch (IOException i) {
            i.printStackTrace();
         }
    	
    	
    }
    
    public Menu deserialize(Scanner input) {
    	Menu menu= null;
    	try {
       
    	  System.out.printf("Digite o nome do arquivo: ");
      	  String fileName = input.next();
    		
          FileInputStream fileIn = new FileInputStream("./"+fileName+".dat");
          ObjectInputStream in = new ObjectInputStream(fileIn);
          
          menu = (Menu) in.readObject();
          in.close();
          fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
  
         } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
    
         }
    	return menu;
    }

}
