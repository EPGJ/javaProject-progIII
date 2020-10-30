package trabalhoprog3java.controller;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import trabalhoprog3java.exception.InvalidReferenceException;
import trabalhoprog3java.exception.NotAnOptionException;
import trabalhoprog3java.exception.NotCharException;


public class ReadData implements Serializable {
	Scanner input;
	
	public ReadData() {
		this.input = new Scanner(System.in);
	}
	public  void clearBuffer() {
        if (input.hasNextLine()) {
        	input.nextLine();
        }
    }
	public void closeInput() {
		this.input.close();
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
		double number = -1;
		try {		
			number = input.nextDouble();
			clearBuffer();
		}catch(NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		return number;
	}
	public Date readDate(){
		Date date ;
		try{
			String line = input.nextLine();
			if(line.split("/").length != 3) {
				throw new InvalidReferenceException(line);
			}
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy"); 
			date = f.parse(line);
		}catch(InvalidReferenceException e) {
			System.out.println("Data com formato invalido: "+ e.getReference());
		}catch(ParseException e) {
			System.out.println("Data invalida" );
		}
		
		
		return null;
	}
	

}
