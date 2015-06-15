package it.cachedownloader.lme2.InputReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderName {

	public List<String> returnNomiEntita() throws IOException {

		BufferedReader TSVFile = new BufferedReader(new FileReader("CacheDown/input_no_object_first200.tsv"));

		int count = 0;
		
		String dataRow = TSVFile.readLine();
		List<String> list = new ArrayList<String>();
		while (dataRow != null) {
			if(count == 200){
				break;
			}
			String[] dataArray = dataRow.split("\t");

			String stringToAdd = dataArray[1];
			list.add(stringToAdd);
			count ++;

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
	
	public List<String> returnSetDiPattern(List<String> listaEntita) throws IOException {

		List<String> list = new ArrayList<String>();

		
		BufferedReader TSVFile = new BufferedReader(new FileReader("CacheDown/PatternPostFirstJob.tsv"));

		String dataRow = TSVFile.readLine();
		while (dataRow != null) {
			String[] dataArray = dataRow.split(":");

			String stringPostValue = dataArray[2].replace(" ", "");
			int valuePattern = Integer.parseInt(stringPostValue);
			
				
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