package it.cachedownloader.lme.FirstJob;

import java.util.List;

import it.cachedownloader.lme.DescriptionParser.Parser;
import it.cachedownloader.lme.InputReader.Reader;

public class Main {
	
	public static Reader reader;
	public static Parser parser;
	
	public static void main(String[] arg) throws Exception {
		
		List<String> setDiQuery = reader.TornaSetDiQuery();
		
		List<String> setDiPattern = parser.TornaLMOutputSet();

	}

}
