package trabalhoprog3java.controller;


import java.util.Scanner;

import trabalhoprog3java.exception.InputMismatchException;
import trabalhoprog3java.exception.InvalidReferenceException;
import trabalhoprog3java.exception.NotAnOptionException;
import trabalhoprog3java.exception.NotCharException;


public class ReadData {
	Scanner input;
	
	public ReadData(Scanner input) {
		this.input = input;
	}
	public  void clearBuffer() {
        if (input.hasNextLine()) {
        	input.nextLine();
        }
    }
	
	public Integer readUserDecision( int numberOptions) {
		
		int userDecision = -1;
		String line = input.nextLine();
		try {
			userDecision = Integer.parseInt(line);
			if(userDecision<=0 || userDecision > numberOptions) {
				throw new NotAnOptionException(userDecision);
			}
			
			return userDecision;
		}catch(NumberFormatException e) {
			System.out.println("O valor informado nao eh inteiro: " + line);
		}catch(NotAnOptionException e) {
			System.out.println(e.getMessage());
		}
		return userDecision;
	}
	
	public Integer readYear(){
		int year;
		String line = input.nextLine();
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
	public char readChar() {
		String line = input.nextLine();

		try {
			if (line.length() > 1) {
				throw new NotCharException(line);				
			}
			return line.charAt(0);
		}catch(NotCharException e) {
			System.out.println(e.getMessage());
		}
		
		return 0;	
	}

	
	public boolean readResponse() {
		char response =  readChar();
		if(response == 'S' || response == 's') {
			return true;
		}
		else {
			return false;
		}
	}
	public String readString() {
		String line = input.nextLine();
		return line;
	}
	public String readLogin() {
		try {
			String login = input.nextLine();
			if(login.split("[.]").length != 2) {
				throw new InvalidReferenceException(login);
			}
			return login;
		}catch(InvalidReferenceException e) {
			System.out.println(e.getMessage());
			
		}
		
		return "invalid";
	}
	public String readPeriod(){
		try {
			String period = input.nextLine();
			if(period.split("/").length != 2) {
				throw new InvalidReferenceException(period);
			}
			return period;	
		}catch(InvalidReferenceException e) {
			System.out.println(e.getMessage());
		}
		return "invalid";
	}
	public Integer readInt() {
		int number = -1 ;
		String line = input.nextLine(); 
		try {
			number = Integer.parseInt(line);
		}catch(NumberFormatException e) {
			System.out.println("O valor informado nao eh inteiro: " + line );
		}
		return number;
	}
	public Double readDouble() {
		Double number = -1.0;
		String line = input.nextLine();
		try {
			number = Double.parseDouble(line);
		}catch(NumberFormatException e) {
			System.out.println("O valor informado nao eh um numero real: " + line );
		}
		return number;
	}

}
