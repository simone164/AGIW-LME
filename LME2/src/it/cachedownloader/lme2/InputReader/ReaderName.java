package it.cachedownloader.lme2.InputReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderName {

	public List<String> returnNomiEntita() throws IOException {

		BufferedReader TSVFile = new BufferedReader(new FileReader("CacheDown/input_no_object_first200.tsv"));

		String dataRow = TSVFile.readLine();
		List<String> list = new ArrayList<String>();
		while (dataRow != null) {

			list.add(dataRow);

			// Iterator<String> it = list.iterator();
			// while (it.hasNext()) {
			// String txt = it.next();
			// System.out.print(txt);
			// }
			// System.out.println(); // Print the data line.
			dataRow = TSVFile.readLine();
		}
		TSVFile.close();
		return list;

	}
	
	public List<String> returnSetDiPattern() throws IOException {

		List<String> list = new ArrayList<String>();

		BufferedReader TSVFile = new BufferedReader(new FileReader("CacheDown/PatternPostFirstJob.tsv"));

		String dataRow = TSVFile.readLine();
		while (dataRow != null) {
				
			String[] stringSplitVirgola = dataRow.split(",");
			String stringKeySporca = stringSplitVirgola[0];
			String[] stringKeySplitted = stringKeySporca.split(":");
			String pattern = stringKeySplitted[1];
			
			list.add(pattern);
				
			// Iterator<String> it = list.iterator();
			// while (it.hasNext()) {
			// String txt = it.next();
			// System.out.print(txt);
			// }
			// System.out.println(); // Print the data line.
			dataRow = TSVFile.readLine();
		}
		TSVFile.close();

		
		return list;

	} // main()


}