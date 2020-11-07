package trabalhoprog3java.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



import trabalhoprog3java.domain.activity.study.Material;
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

	public long validateNumber(String data) throws NumberFormatException{
		long number = Long.parseLong(data);
		return number;
	}

	public Date validateDate(String data) throws InvalidReferenceException, ParseException {
		Date date = null;		
		if(data.split("/").length != 3) {
			throw new InvalidReferenceException(data);
		}
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy"); 
		date = f.parse(data);
		
		return date;
	}

	public String validateDisciplineReference(String data) throws InvalidReferenceException{
		String [] dataParts = data.split("-");
		if(dataParts.length!=2) {
			throw new InvalidReferenceException(data);
		}
		String periodReference = validatePeriodReference(dataParts[1]);
		return data;
	}
	
	public String validatePeriodReference(String data)throws InvalidReferenceException{
		
		String [] dataParts = data.split("/");
		if(dataParts.length!=2) {
			throw new InvalidReferenceException(data);
		}
		return data;
	}
	public String validateTime(String data)throws InvalidReferenceException {
		String [] dataParts = data.split(":");
		if(dataParts.length!=2) {
			throw new InvalidReferenceException(data);
		}
		return data;
		
	}

	public List<Material> validateMaterials(String data) {
		
		List<Material> materials =  new ArrayList<>();
		String[] dataParts = data.split("[)]");
		String materialName;
		String materialLink;
		for(String dataPart: dataParts) {
			String [] aux = dataPart.split("[\\]][(]");
			materialLink = aux[1];
			materialName = aux[0].replaceAll("\\[", "");
			materials.add(new Material(materialName,materialLink));	
		}
		
		return materials;
	}
	

	public Double validateDouble(String data)throws NumberFormatException {
		double number = Double.parseDouble(data);
		return number;
	}
	

	


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


}
