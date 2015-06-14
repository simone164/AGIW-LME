package it.cachedownloader.lme.queryBing;

import it.cachedownloader.lme.queryBing.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.json.*;

public class QuerySender {

    public List<String[]> queryToDescription(List<String> preparaQuery) throws Exception {
        final String accountKey = "0BurpGvn2zX75hWORRHEGtMtuS5FhZLE9A3Gi32AvU4";
        final String bingUrlPattern = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%%27%s%%27&$format=JSON";

        List<String[]> list = new ArrayList<String[]>();
                
        for (String s : preparaQuery) {
        	List<String> descriptions = new ArrayList<String>();
        	
        	final String query = URLEncoder.encode(s, Charset.defaultCharset().name());
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
                    descriptions.add(description);
                   // System.out.println(aResult.get("Description"));
                }
                    //System.out.println(response.toString());
                }
            
            list.add(descriptions.toArray(new String[descriptions.size()]));
            }

        return list;
        }
        
}