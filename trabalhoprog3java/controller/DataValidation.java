package trabalhoprog3java.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import trabalhoprog3java.exception.InvalidReferenceException;
import trabalhoprog3java.exception.NotAnOptionException;
import trabalhoprog3java.exception.NotCharException;

public class DataValidation implements Serializable {

	public Integer validateYear(String data) throws NumberFormatException, NotAnOptionException {
		int year = 0;
		year = Integer.parseInt(data);
		if (year <= 0) {
			throw new NotAnOptionException(year);
		}
		else {			
			return year;
		}
	}

	public char validateChar(String data) throws NotCharException {

		if (data.length() > 1) {
			throw new NotCharException(data);
		}
		return data.charAt(0);
	}

	public String validateLogin(String login) throws InvalidReferenceException {
		if(login.split("[.]").length != 2) {
			throw new InvalidReferenceException(login);
		}
		else {
			return login;
		}
	}

	public int validateInt(String data)throws NumberFormatException {
		int number = Integer.parseInt(data);
		return number;
	}


//	public Integer readInt() {
//	int number = -1 ;
//	String line = input.nextLine(); 
//	try {
//		number = Integer.parseInt(line);
//	}catch(NumberFormatException e) {
//		System.out.println("O valor informado nao eh inteiro: " + line );
//	}
//	return number;
//}
	
	
	

//	
//	
//
//	
//	public boolean readResponse() {
//		char response =  readChar();
//		if(response == 'S' || response == 's') {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
//	public String readString() {
//		String line = input.nextLine();
//		return line;
//	}
//	
//	public String readPeriod(){
//		try {
//			String period = input.nextLine();
//			if(period.split("/").length != 2) {
//				throw new InvalidReferenceException(period);
//			}
//			return period;	
//		}catch(InvalidReferenceException e) {
//			System.out.println(e.getMessage());
//		}
//		return "invalid";
//	}

//	public Double readDouble() {
//		double number = -1;
//		try {		
//			number = input.nextDouble();
//			clearBuffer();
//		}catch(NumberFormatException e) {
//			System.out.println(e.getMessage());
//		}
//		return number;
//	}
//	public Date readDate(){
//		Date date =null ;
//		try{
//			String line = input.nextLine();
//			if(line.split("/").length != 3) {
//				throw new InvalidReferenceException(line);
//			}
//			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy"); 
//			date = f.parse(line);
//			
//		}catch(InvalidReferenceException e) {
//			System.out.println("Data com formato invalido: "+ e.getReference());
//		}catch(ParseException e) {
//			System.out.println("Data invalida" );
//		}
//		
//		
//		return date;
//	}

}
