package it.cachedownloader.lme.DescriptionParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
	
	//public static void main(final String[] args) throws Exception{
	
	public List<String> TornaLMOutputSet() throws IOException{
		
		BufferedReader TSVFile = new BufferedReader(new FileReader(
				"CacheDown/place_of_birt_LMcomplete.tsv"));

		String dataRow = TSVFile.readLine();
		List<String> list = new ArrayList<String>();
		while (dataRow != null) {
			list.clear();

			String stringToAdd = dataRow.toString();

			list.add(stringToAdd);

//			Iterator<String> it = list.iterator();
//			while (it.hasNext()) {
//				String txt = it.next();
//				System.out.print(txt);
//			}
//			System.out.println(); // Print the data line.
//			dataRow = TSVFile.readLine();
		}

		TSVFile.close();

		//System.out.println();
		
		return list;

	} // main()
	
	
	public void parsaPattern(List<String> patternSet, List<String> DescriptionSet){
		
	}

	

}