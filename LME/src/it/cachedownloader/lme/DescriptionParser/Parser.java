package it.cachedownloader.lme.DescriptionParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

	//public static void main(final String[] args) throws Exception{

	public List<String> returnLMOutputSet() throws IOException {
		BufferedReader TSVFile = new BufferedReader(new FileReader("CacheDown/place_of_birt_LMcomplete.tsv"));

		String dataRow = TSVFile.readLine();
		List<String> list = new ArrayList<String>();
		while (dataRow != null) {

			String stringToAdd = dataRow.toString();

			list.add(stringToAdd);

//			Iterator<String> it = list.iterator();
//			while (it.hasNext()) {
//				String txt = it.next();
//				//System.out.print(txt);
//			}
//			//System.out.println(); // Print the data line.
			dataRow = TSVFile.readLine();
			
			
		}

		TSVFile.close();

		return list;

	} 
	
	public void parsaPattern(List<String> patternSet, List<String[]> descriptionSet) {
		Map<String , Integer> countPattern = new HashMap<String, Integer>();
		
		
		for(String s : patternSet){
			for (String [] arrayString : descriptionSet ){
				
				for (int y = 0; y < arrayString.length ; y++) {
					
					String string = arrayString[y];
					if (string.contains(s)) {
						if (countPattern.containsKey(s)) {
							int i = countPattern.get(s).intValue();
							countPattern.put(s, new Integer(i+1));
						} else {
							countPattern.put(s, new Integer(1));
						}
					}
				}
			}
		}
		
		for (Map.Entry<String, Integer> entry : countPattern.entrySet()) {
	        String key = entry.getKey().toString();;
	        Integer value = entry.getValue();
	        System.out.println("key: " + key + ", value: " + value );
	    }
	}

}
