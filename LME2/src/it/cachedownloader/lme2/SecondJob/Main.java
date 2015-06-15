package it.cachedownloader.lme2.SecondJob;

//import it.cachedownloader.lme2.DescriptionParser.Parser;
import it.cachedownloader.lme2.InputReader.ReaderName;
//import it.cachedownloader.lme2.queryBing.QuerySender;

import java.util.Iterator;
import java.util.List;

public class Main {
	
	public static final ReaderName reader = new ReaderName();
	//public static final Parser parser = new Parser();
	//public static final QuerySender sender = new QuerySender();
	
	public static void main(String[] arg) throws Exception {
		
		List<String> setDiEntita = reader.returnNomiEntita();	//chiavi e nomi
		
		List<String> setDiPattern = reader.returnSetDiPattern();
		
		System.out.println(setDiPattern.size());
		
		Iterator<String> it = setDiPattern.iterator();
		 while (it.hasNext()) {
		 String txt = it.next();
		 System.out.println(txt);
		 }

	}

}