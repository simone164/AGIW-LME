package it.cachedownloader.lme2.queryBing;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class QueryBing {

	public Map<String, List<String>> queryToDescription(List<String> setNomi, List<String> setPatterns) throws Exception {
		final String accountKey = "o9mLVYEI8o04CVhdQcN/CEAHtO4h5bSq71RwmA1yai8";
		final String bingUrlPattern = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%%27%s%%27&$format=JSON";

		Map<String, List<String>> mappa = new HashMap<String, List<String>>();
		
		int numeroQuery = 0;

		for (String s : setNomi) {
			numeroQuery++;
			
			List<String> list = new ArrayList<String>();

			// splitto la stringa iniziale per ottenere solo nome e cognome
			String[] split = s.split("\t");
			String nomeCognome = split[1];

			System.out.println("s: " + nomeCognome +" # " + numeroQuery);
			
			// cilco per ogni pattern
			for (String pattern : setPatterns) {
				if (numeroQuery < 201) {

					//System.out.println("s+patter: " + nomeCognome + pattern);
					
					final String query = URLEncoder.encode(nomeCognome+pattern, Charset.defaultCharset().name());
					final String bingUrl = String.format(bingUrlPattern, query);

					final String accountKeyEnc = Base64.getEncoder().encodeToString((accountKey + ":" + accountKey).getBytes());

					final URL url = new URL(bingUrl);
					final URLConnection connection = url.openConnection();
					connection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);

					try (final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
						String inputLine;

						final StringBuilder response = new StringBuilder();
						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}

						byte[] bytes = response.toString().getBytes();
						InputStream inputStream = new ByteArrayInputStream(bytes);

						JsonReader jasonReader = Json.createReader(inputStream);
						JsonObject json = jasonReader.readObject();
						JsonObject d = json.getJsonObject("d");
						JsonArray results = d.getJsonArray("results");
						int resultsLength = results.size();

						for (int i = 0; i < resultsLength; i++) {
							JsonObject aResult = results.getJsonObject(i);
							String description = aResult.get("Description").toString();
							list.add(description);
							//System.out.println("Description: " + aResult.get("Description"));
						}
					}
				}
			}
			
			mappa.put(s, list);
		}

		return mappa;
	}
}