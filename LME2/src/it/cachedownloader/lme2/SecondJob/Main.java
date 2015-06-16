package it.cachedownloader.lme2.SecondJob;

//import it.cachedownloader.lme2.DescriptionParser.Parser;
import it.cachedownloader.lme2.InputReader.ReaderName;
//import it.cachedownloader.lme2.queryBing.QuerySender;

import it.cachedownloader.lme2.LocationParser.LocationParser;
import it.cachedownloader.lme2.queryBing.QueryBing;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {

	public static final ReaderName reader = new ReaderName();
	public static final QueryBing qb = new QueryBing();
	public static final LocationParser parser = new LocationParser();

	public static void main(String[] arg) throws Exception {

		List<String> setDiEntita = reader.returnNomiEntita(); // chiavi e nomi

		List<String> setDiPattern = reader.returnSetDiPattern(); // pattern da
																	// usare

		Map<String, List<String>> mappaEntities2Descriptions = qb
				.queryToDescription(setDiEntita, setDiPattern);

		List<String> lista = parser.parsaPattern(mappaEntities2Descriptions, setDiPattern);

		// System.out.println(setDiPattern.size());

		if (!lista.isEmpty()) {

			Iterator<String> it = lista.iterator();
			while (it.hasNext()) {
				String txt = it.next();
				System.out.println(txt);
			}

		} else {
			System.out.println("LISTA DELLE DESCRIPTION VUOTA");
		}
	}

}