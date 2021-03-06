package it.cachedownloader.lme.DescriptionParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
	//public static void main(final String[] args) throws Exception
	
	public List<String> returnLMOutputSet() throws IOException {
		BufferedReader TSVFile = new BufferedReader(new FileReader("CacheDown/place_of_birt_LMcomplete.tsv"));

		String dataRow = TSVFile.readLine();
		List<String> list = new ArrayList<String>();
		while (dataRow != null) {
			String stringToAdd = dataRow.toString();
			list.add(stringToAdd);
			dataRow = TSVFile.readLine();
			
		}
		TSVFile.close();

		return list;
	} 
	
	public Map<String , Integer> parsaPattern(List<String> patternSet, List<String[]> descriptionSet) {
		Map<String , Integer> countPattern = new HashMap<String, Integer>();
		
		for(String s : patternSet){
			for (String [] arrayString : descriptionSet ){
				
				for (int y = 0; y < arrayString.length ; y++) {
					
					String string = arrayString[y];
					if (string.equals(s)) {
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
		
	return countPattern;
	}
	
	public static int distance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }

	
	
	
	
}