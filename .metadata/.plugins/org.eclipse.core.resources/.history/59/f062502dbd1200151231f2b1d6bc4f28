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
//		int i = 0;
		while (dataRow != null) {
//			i++;
			String[] dataArray = dataRow.split("\t");

			String stringToAdd = dataArray[1] + "-" + dataArray[3];
			
			//System.out.println("stringa: " + stringToAdd);
			
			list.add(stringToAdd);

			// Iterator<String> it = list.iterator();
			// while (it.hasNext()) {
			// String txt = it.next();
			// System.out.print(txt);
			// }
			// System.out.println(); // Print the data line.
			dataRow = TSVFile.readLine();
		}

		TSVFile.close();

		System.out.println("GRANDEZZA: " +list.size());
		
		
		return list;

	} // main()

}