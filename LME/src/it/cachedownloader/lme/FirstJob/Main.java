package it.cachedownloader.lme.FirstJob;

import it.cachedownloader.lme.DescriptionParser.Parser;
import it.cachedownloader.lme.InputReader.Reader;

import java.util.List;

public class Main {
	
	public static Reader reader;
	public static Parser parser;
	
	public static void main(String[] arg) throws Exception {
		
		List<String> setDiQuery = reader.TornaSetDiQuery();
		
		List<String> setDiPattern = parser.TornaLMOutputSet();

	}

}
