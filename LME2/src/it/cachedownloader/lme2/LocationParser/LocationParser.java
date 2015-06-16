package it.cachedownloader.lme2.LocationParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LocationParser {

	public List<String> parsaPattern(
			Map<String, List<String>> entityToSetDescript,
			List<String> listPattern) throws IOException {
		List<String> stringheFinali = new ArrayList<String>();

		List<String> localitaConKey = listaLocation();

		System.out.println("VALORE MAPPA = " + entityToSetDescript.size());

		for (Map.Entry<String, List<String>> entry : entityToSetDescript
				.entrySet()) {

			Map<String, Integer> counterLocalita = new HashMap<String, Integer>();

			String entita = entry.getKey().toString();
			;

			System.out.println("STO LAVORANDO LA CHIAVE = " + entita);

			List<String> descriptionsForEntitySet = entry.getValue();

			if (!(descriptionsForEntitySet.isEmpty())) {

				System.out.println("ENTRATO NEL EMPTY");

				int i = 0;

				Iterator<String> it = descriptionsForEntitySet.iterator();
				while (it.hasNext()) {
					i++;
					System.out.println(i);
					String testoDescript = it.next();

					counterLocalita = tornaMappaLocationCountate(testoDescript,
							localitaConKey, counterLocalita, listPattern);

				}

				String localitaProbabile = tornaLocalitaProbabile(counterLocalita);

				stringheFinali.add(entita + " - " + localitaProbabile);

			} else {
				stringheFinali.add("");
			}

		}

		return stringheFinali;
	}

	public String tornaLocalitaProbabile(Map<String, Integer> counterLocalita) {

		String localita = "";

		int max = Collections.max(counterLocalita.values());

		for (Map.Entry<String, Integer> entry : counterLocalita.entrySet()) {

			int valore = entry.getValue();
			if (valore == max) {
				localita += entry.getKey() + " ";
			}
		}

		System.out.println(localita);
		return localita;
	}

	public Map<String, Integer> tornaMappaLocationCountate(String descriptions,
			List<String> localitaConKey, Map<String, Integer> counterLocalita,
			List<String> listaPattern) {

		Iterator<String> it = localitaConKey.iterator();
		while (it.hasNext()) {
			String localitaSporca = it.next();
			String[] localitaSplitted = localitaSporca.split("\t");
			String localita = localitaSplitted[1];

			for (String pattern : listaPattern) {

				if (descriptions.contains(pattern + " " + localita)) {
					// System.out.println("Matcha : "+ localita);
					if (counterLocalita.containsKey(localitaSporca)) {
						int i = counterLocalita.get(localitaSporca).intValue();
						counterLocalita.put(localitaSporca, new Integer(i + 1));
						// System.out.println("L'ha trovata"+ i);
					} else {
						counterLocalita.put(localitaSporca, new Integer(1));
					}
				}
			}

		}

		return counterLocalita;
	}

	public List<String> listaLocation() throws IOException {

		List<String> list = new ArrayList<String>();

		BufferedReader TSVFile = new BufferedReader(new FileReader(
				"CacheDown/location.tsv"));

		String dataRow = TSVFile.readLine();
		while (dataRow != null) {

			list.add(dataRow);

			dataRow = TSVFile.readLine();
		}
		TSVFile.close();

		return list;

	}

}
