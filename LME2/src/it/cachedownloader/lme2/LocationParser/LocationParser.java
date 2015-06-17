package it.cachedownloader.lme2.LocationParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LocationParser {

	public Map<String, List<String>> tornaMappaEntitiesToQuery(Map<String, List<String>> entityToSetDescript, List<String> listPattern)
			throws IOException {
		Map<String, List<String>> mappaEntitaToQuery = new HashMap<String, List<String>>();

		int i = 0;

		for (Map.Entry<String, List<String>> entry : entityToSetDescript.entrySet()) {
			i++;
			System.out.println("parso #" + i);
			
			List<String> listaDescriPulite = new ArrayList<String>();

			String entita = entry.getKey().toString();
			String[] entSplitted = entita.split("\t");
			String nome = entSplitted[1];

			List<String> descriptionsForEntitySet = entry.getValue();

			if (!(descriptionsForEntitySet.isEmpty())) {
				// int i = 0;
				Iterator<String> it = descriptionsForEntitySet.iterator();
				while (it.hasNext()) {
					// i++;
					String testoDescript = it.next();
					listaDescriPulite = tornaDescrPulite(testoDescript, listaDescriPulite, listPattern, nome);
				}

			} else {
				// stringheFinali.add("");
			}

			mappaEntitaToQuery.put(entita, listaDescriPulite);
		}
		return mappaEntitaToQuery;
	}

	@SuppressWarnings("unused")
	public List<String> tornaDescrPulite(String testoDescript, List<String> listaDescriPulite, List<String> listPattern, String entita) {

		for (String pattern : listPattern) {
			String stringPerContains = entita + pattern;
		
			String stringDaAddare = "";
			String stringFinale = "";

			if (testoDescript.contains(stringPerContains)) {
				String[] parolePattern = pattern.split("\\s+");
				String[] paroleDescript = testoDescript.split("\\s+");

				for (int i = 0; i < paroleDescript.length; i++) {
					if (paroleDescript[i].equals(parolePattern[1])) {
						int contaPat = 0;
						for (String s : parolePattern) {
							contaPat++;
						}

						// QUESTO PERCHé IL PRIMO SPLIT é UNO SPAZIO BIANCO
						contaPat = contaPat - 1;

						String paroleAfter = "";

						if (paroleDescript.length > i + contaPat + 1) {
							paroleAfter += paroleDescript[i + contaPat + 1] + " ";
							if (paroleDescript.length > i + contaPat + 2) {
								paroleAfter += paroleDescript[i + contaPat + 2] + " ";
								if (paroleDescript.length > i + contaPat + 3) {
									paroleAfter += paroleDescript[i + contaPat + 3] + " ";
									if (paroleDescript.length > i + contaPat + 4) {
										paroleAfter += paroleDescript[i + contaPat + 4] + " ";
										if (paroleDescript.length > i + contaPat + 5) {
											paroleAfter += paroleDescript[i + contaPat + 5] + " ";

											stringFinale += paroleAfter + " ";

										}
									}

								}
							}
						}

					}

				}
			}

			if (!stringFinale.equals("")) {

				stringDaAddare = StanfordNER.identifyNER(stringFinale,
						"CacheDown/stanford-ner-2014-01-04/classifiers/english.all.3class.distsim.crf.ser.gz")
						.toString();

				if (!stringDaAddare.equals("")) {
					listaDescriPulite.add(stringDaAddare);
				}

			}

		}

		return listaDescriPulite;
	}

	public List<String> listaLocation() throws IOException {

		List<String> list = new ArrayList<String>();

		BufferedReader TSVFile = new BufferedReader(new FileReader("CacheDown/location.tsv"));

		String dataRow = TSVFile.readLine();
		while (dataRow != null) {

			list.add(dataRow);

			dataRow = TSVFile.readLine();
		}
		TSVFile.close();

		return list;

	}

}
