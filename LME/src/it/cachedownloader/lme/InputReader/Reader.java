package it.cachedownloader.lme.InputReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {

	public List<String> returnSetDiQuery() throws IOException {

		BufferedReader TSVFile = new BufferedReader(new FileReader("CacheDown/triples_actor_city_5000.tsv"));

		String dataRow = TSVFile.readLine();
		List<String> list = new ArrayList<String>();
		while (dataRow != null) {
			String[] dataArray = dataRow.split("\t");

			String stringToAdd = dataArray[1] + "-" + dataArray[3];
			list.add(stringToAdd);
			dataRow = TSVFile.readLine();
		}
		TSVFile.close();
		return list;

	} 

}