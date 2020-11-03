package trabalhoprog3java;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import trabalhoprog3java.domain.Period;


public class MainCsv implements Serializable{
    public static void main(String[] args) throws Exception {
    	Map<String, String> fileList = new HashMap<>();;
		
    	
    	for(int i =0;i< args.length;i++) {

    		if(args[i].equals("-p")) fileList.put("periodsFile", args[i+1]);
    		if(args[i].equals("-d")) fileList.put("teachersFile", args[i+1]);
    		if(args[i].equals("-o")) fileList.put("diciplinesFile", args[i+1]);
    		if(args[i].equals("-e")) fileList.put("studentsFile", args[i+1]);
    		if(args[i].equals("-m")) fileList.put("enrollmentsFile", args[i+1]);
    		if(args[i].equals("-a")) fileList.put("activitiesFile", args[i+1]);
    		if(args[i].equals("-n")) fileList.put("activitiesRatingFile", args[i+1]);
    		if(args[i].equals("--read-only")) {}
    		if(args[i].equals("--write-only")) {}		
    	}
    	
		
		fileList.forEach((key, file) ->
			System.out.println(file)
		);
    	
    	
    	
    }
}