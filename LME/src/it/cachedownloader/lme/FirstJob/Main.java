package it.cachedownloader.lme.FirstJob;

import it.cachedownloader.lme.DescriptionParser.Parser;
import it.cachedownloader.lme.InputReader.Reader;
import it.cachedownloader.lme.queryBing.QuerySender;

import java.util.List;

public class Main {
	
	public static final Reader reader = new Reader();
	public static final Parser parser = new Parser();
	public static final QuerySender sender = new QuerySender();
	
	public static void main(String[] arg) throws Exception {
		
		List<String> setDiQuery = reader.returnSetDiQuery();
		
		List<String[]> setDiDescriptions = sender.queryToDescription(setDiQuery);
		
		List<String> setDiPattern = parser.returnLMOutputSet();
		
		parser.parsaPattern(setDiPattern, setDiDescriptions);

		
		
	}

}
