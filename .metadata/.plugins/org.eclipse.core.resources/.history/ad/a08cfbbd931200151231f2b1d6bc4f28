package it.cachedownloader.lme.FirstJob;

import it.cachedownloader.lme.DescriptionParser.Parser;
import it.cachedownloader.lme.InputReader.Reader;
import it.cachedownloader.lme.queryBing.QuerySender;

import java.util.List;

public class Main {
	
	public static Reader reader;
	public static Parser parser;
	public static QuerySender sender;
	
	public static void main(String[] arg) throws Exception {
		
		List<String> setDiQuery = reader.TornaSetDiQuery();
		
		List<String> setDiDescriptions = sender.queryToDescription(setDiQuery);
		
		List<String> setDiPattern = parser.TornaLMOutputSet();
		
		parser.parsaPattern(setDiPattern, setDiDescriptions);

		
		
	}

}
