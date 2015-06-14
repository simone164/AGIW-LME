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
        
        System.out.println("count prepara query: " + preparaQuery.size());
        int numeroQuery = 0;
        for (String s : preparaQuery) {
        	numeroQuery++;
        	if (numeroQuery < 100) {
	        	
        		s.replace('-', ' ');
        		
	        	System.out.println("Query:" + s);
	        	
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
	                    description = filtraDescriptions(description, s);
	                    if(!description.equals("null")){
	                    	System.out.println("DESC: " + description);
	                    }
	                    descriptions.add(description);
	                   // System.out.println(aResult.get("Description"));
	                }
	                    //System.out.println(response.toString());
	                }
	            
	            list.add(descriptions.toArray(new String[descriptions.size()]));
            }
        }

        return list;
        }
    
    
    	
    public String filtraDescriptions(String description, String query){
    	
    	String result ="";
    	
    	String[] querySplitted = query.split("-");
    	String entita = querySplitted[0];
    	String luogo = querySplitted[1];
    	String cognome = "";
    	
    	String[] entitaSplitted = entita.split("\\s+");
//    	for(int i=0; i < entitaSplitted.length; i++){
//    		cognome =  entitaSplitted[i];
//    	}
    	
    	cognome = entitaSplitted[entitaSplitted.length-1];
    	
    	//System.out.println("Cognome: " + cognome);
    	//System.out.println("Luogo: " + luogo);
    	
    	boolean trovato = false;
    	boolean daPrendere = false;
    	
    	String[] descrSplitted = description.split("\\s+");
    	for(int i = 0; i< descrSplitted.length ; i++){
    		if(descrSplitted[i].equals(cognome)){
    				trovato = true;
    		}
    		
    		if(trovato){
    			result += descrSplitted[i] + " ";
    		}
    		
    		
    		if(descrSplitted[i].equals(luogo)){
    				daPrendere = true;
    			break;
    		}
    	}
    	
    	if(daPrendere){
        	
    		return result;
    	
    	}else{
    		return result = "null";
    	}
    	

    }
    



}