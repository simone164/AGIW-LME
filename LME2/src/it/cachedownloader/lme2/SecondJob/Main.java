package it.cachedownloader.lme2.SecondJob;

//import it.cachedownloader.lme2.DescriptionParser.Parser;
import it.cachedownloader.lme2.InputReader.ReaderName;
//import it.cachedownloader.lme2.queryBing.QuerySender;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
	
	public static final ReaderName reader = new ReaderName();
	//public static final Parser parser = new Parser();
	//public static final QuerySender sender = new QuerySender();
	
	public static void main(String[] arg) throws Exception {
		
		List<String> setDiEntita = reader.returnNomiEntita();
		
		List<String> setDiPattern = reader.returnSetDiPattern(setDiEntita);
		
		System.out.println(setDiPattern.size());
		
		Iterator<String> it = setDiPattern.iterator();
		 while (it.hasNext()) {
		 String txt = it.next();
		 System.out.println(txt);
		 }
		
		  
		
//		List<String[]> setDiDescriptions = sender.queryToDescription(setDiQuery);
//		
//		List<String> setDiPattern = parser.returnLMOutputSet();
//		
//		Map<String , Integer> map = parser.parsaPattern(setDiPattern, setDiDescriptions);
//
//		for (Map.Entry<String, Integer> entry : map.entrySet()) {
//	        String key = entry.getKey().toString();;
//	        Integer value = entry.getValue();
//	        System.out.println("key: " + key + ", value: " + value );
//	    }
		
	}

}