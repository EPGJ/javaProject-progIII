package trabalhoprog3java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CsvReader {
	
	private BufferedReader reader;
	private String[] line;
	

	public CsvReader(String path) throws IOException {
		this.reader = new BufferedReader(new FileReader(path));
		
	}
	
	public String[] nextLine(){
		try {
			this.line = this.reader.readLine().split(";");
			return this.line;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void changeFile(String path) throws IOException {
		this.reader = new BufferedReader(new FileReader(new File(path)));
	}
	
	
}
