package trabalhoprog3java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CsvReader {
	
	private BufferedReader reader;
	private String[] line;
	private Map<String, String> fileList;
	
	

	public CsvReader(Map<String, String> fileList) throws IOException {
		this.setFileList(fileList);
	}
	
	public String[] nextLine(){
		try {
			this.line = this.reader.readLine().split(";");
			return this.line;
		} catch (Exception e) {
			return null;
		}
	}

	public Map<String, String> getFileList() {
		return fileList;
	}

	public void setFileList(Map<String, String> fileList) {
		this.fileList = fileList;
	}
	
}
