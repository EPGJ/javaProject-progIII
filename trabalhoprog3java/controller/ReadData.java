package trabalhoprog3java.controller;

import java.util.Scanner;

import trabalhoprog3java.exception.NotAnOptionException;
import trabalhoprog3java.exception.NotCharException;


public class ReadData {
	public Scanner input;
	
	public ReadData(Scanner input) {
		this.input = input;
	}
	
	public Integer readUserDecision( int numberOptions) {
		int userDecision;
		String line = input.next();
		try {
			userDecision = Integer.parseInt(line);
			if(userDecision<=0 || userDecision > numberOptions) {
				throw new NotAnOptionException(userDecision);
			}
			
			return userDecision;
		}catch(NumberFormatException e) {
			System.out.println("O valor informado nao eh inteiro: " + line);
		}catch(NotAnOptionException e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public Integer readYear(){
		int year;
		String line = input.next();
		try {
			year = Integer.parseInt(line);
			if(year <= 0) {
				throw new NotAnOptionException(year);
			}
			return year;
		}catch(NumberFormatException e) {
			System.out.println("O valor informado nao eh inteiro: " + line );
		}catch(NotAnOptionException e) {
			System.out.println("Ano invalido ");
		}
		return -1;
	}
	public char readSemester() {
		String line = input.next();

		try {
			if (line.length() > 1) {
				throw new NotCharException(line);				
			}
			return line.charAt(0);
		}catch(NotCharException e) {
			System.out.println(e);
		}
		
		return 0;	
	}

}
