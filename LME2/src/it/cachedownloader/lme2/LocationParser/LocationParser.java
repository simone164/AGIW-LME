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

	public Map<String, List<String>> tornaMappaEntitiesToQuery(Map<String, List<String>> entityToSetDescript, List<String> listPattern) throws IOException {

		System.out.println("VALORE MAPPA = " + entityToSetDescript.size());

		Map<String, List<String>> mappaEntitaToQuery = new HashMap<String, List<String>>();

		for (Map.Entry<String, List<String>> entry : entityToSetDescript.entrySet()) {

			List<String> listaDescriPulite = new ArrayList<String>();

			String entita = entry.getKey().toString();

			List<String> descriptionsForEntitySet = entry.getValue();

			if (!(descriptionsForEntitySet.isEmpty())) {

				// int i = 0;

				Iterator<String> it = descriptionsForEntitySet.iterator();
				while (it.hasNext()) {
					// i++;

					String testoDescript = it.next();

					// System.out.println(" CHIAMATO IL METODO TORNA QUERY");

					listaDescriPulite = tornaDescrPulite(testoDescript, listaDescriPulite, listPattern);
				}

			} else {
				// stringheFinali.add("");
			}

			mappaEntitaToQuery.put(entita, listaDescriPulite);

		}

		return mappaEntitaToQuery;
	}

	@SuppressWarnings("unused")
	public List<String> tornaDescrPulite(String testoDescript, List<String> listaDescriPulite, List<String> listPattern) {

		for (String pattern : listPattern) {

			String stringDaAddare = "";

			if (testoDescript.contains(pattern)) {

				// System.out.println(" trovato il metodo cerca pattern");

				String[] parolePattern = pattern.split("\\s+");
				String[] paroleDescript = testoDescript.split("\\s+");

				// System.out.println("INIZIO PATTERN");
				// for (String s : parolePattern) {
				// System.out.println(s);
				// }
				// System.out.println("FINE PATTERN");
				//

				for (int i = 0; i < paroleDescript.length; i++) {

					// System.out.println(paroleDescript[i] + " vs "
					// +parolePattern[0]);

					if (paroleDescript[i].equals(parolePattern[1])) {

						System.out.println(" trovata la prima parola del pattern");

						int contaPat = 0;
						for (String s : parolePattern) {
							contaPat++;
						}

						// QUESTO PERCHé IL PRIMO SPLIT é UNO SPAZIO BIANCO
						contaPat = contaPat - 1;

						if (paroleDescript.length > i + contaPat + 1) {
							stringDaAddare += paroleDescript[i + contaPat + 1] + " ";
							if (paroleDescript.length > i + contaPat + 2) {
								stringDaAddare += paroleDescript[i + contaPat + 2] + " ";
								if (paroleDescript.length > i + contaPat + 3) {
									stringDaAddare += paroleDescript[i + contaPat + 3] + " ";
								}
							}

						}

					}

				}
			}

			if (!stringDaAddare.equals("")) {
				System.out.println(stringDaAddare);
				listaDescriPulite.add(stringDaAddare);
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
