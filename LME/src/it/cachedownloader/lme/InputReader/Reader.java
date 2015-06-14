package it.cachedownloader.lme.InputReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Reader {

	public static void main(String[] arg) throws Exception {

		BufferedReader TSVFile = new BufferedReader(new FileReader(
				"tsvfile.tsv"));

		String dataRow = TSVFile.readLine();
		List<String> list = new ArrayList<String>();
		while (dataRow != null) {
			String[] dataArray = dataRow.split("\t");
			for (String item : dataArray) {

				list.add(item);

			}
			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				list.clear();
				String txt = it.next();
				System.out.print(txt);
			}
			System.out.println(); // Print the data line.
			dataRow = TSVFile.readLine();
		}

		TSVFile.close();

		System.out.println();

	} // main()
}