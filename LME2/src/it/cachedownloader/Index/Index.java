package it.cachedownloader.Index;

import it.cachedownloader.lme2.LocationParser.LocationParser;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Index {

	public static final LocationParser parser = new LocationParser();

//	public static void main(String[] args) throws IOException, ParseException {
//
//		// creaIndex();
//
//		//searchIndex("Rome di notteee");
//
//	}

	public static void search(Map<String, List<String>> mappa) throws IOException, ParseException {
		StandardAnalyzer analyzer = new StandardAnalyzer(CharArraySet.EMPTY_SET);
		Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("index/indice"));

		/* set the maximum number of results */
		int maxHits = 1000;

		/* open a directory reader and create searcher and topdocs */
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(maxHits);

		/* create the query parser */
		QueryParser qp = new QueryParser("localita", analyzer);

		// /* query string */
		// Query q = qp.parse("cerca");
		//
		// /* search into the index */
		// searcher.search(q, collector);
		// ScoreDoc[] hits = collector.topDocs().scoreDocs;

	}

	public static void creaIndex() throws IOException {

		/* create a standard analyzer */
		StandardAnalyzer analyzer = new StandardAnalyzer(CharArraySet.EMPTY_SET);

		/* create the index in the pathToFolder or in RAM (choose one) */
		Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("index/indice"));

		/* set an index congif */
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		config.setOpenMode(OpenMode.CREATE);

		/* create the writer */
		IndexWriter writer = new IndexWriter(directory, config);

		List<String> listaLocation = parser.listaLocation();
		for (String localitaSporca : listaLocation) {
			String[] localitaSplitted = localitaSporca.split("\t");
			String chiave = localitaSplitted[0];
			String localita = localitaSplitted[1];

			Document doc = new Document();
			doc.add(new StringField("chiave", chiave, Field.Store.YES));
			doc.add(new TextField("localita", localita, Field.Store.YES));

			writer.addDocument(doc);

		}

		/* close the writer */
		writer.close();

	}

	public List<String> searchIndex(Map<String, List<String>> mappa) throws IOException, ParseException {
		// System.out.println("Searching for '" + searchString + "'");

		List<String> listaOutputs = new ArrayList<String>();

		StandardAnalyzer analyzer = new StandardAnalyzer(CharArraySet.EMPTY_SET);
		Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("index/indice"));

		/* set the maximum number of results */
		int maxHits = 1000000;

		/* open a directory reader and create searcher and topdocs */
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		

		/* create the query parser */
		QueryParser qp = new QueryParser("localita", analyzer);

		for (Map.Entry<String, List<String>> entry : mappa.entrySet()) {
			String entita = entry.getKey();
			// ScoreDoc[] hits;

			Map<String, Integer> risultato = new HashMap<String, Integer>();

			for (String query : entry.getValue()) {

				TopScoreDocCollector collector = TopScoreDocCollector.create(maxHits);
				
				BooleanQuery inBoolQuery = new BooleanQuery();
				
				String[] querySplittata = query.split("\\s+");
				for(String s: querySplittata){
					
					//Query termQuery = qp.parse(s);
					Query termQuery = qp.parse(QueryParser.escape(s));
					inBoolQuery.add(termQuery, Occur.SHOULD);
				}
				
//				/* create a query from 3 words */
//				String term1 = "rome";
//				String term2 = "� bella";
//				String term3 = "di notte";
//				Query termQuery1 = qp.parse(term1);
//				Query termQuery2 = qp.parse(term2);
//				Query termQuery3 = qp.parse(term3);
//				/* create inner boolean query */
//				BooleanQuery in = new BooleanQuery();
//				in.add(termQuery2, Occur.MUST_NOT);
//				in.add(termQuery3, Occur.MUST_NOT);
//				/* create outer boolean query */
//				BooleanQuery out = new BooleanQuery();
//				out.add(in, Occur.SHOULD);
//				out.add(termQuery1, Occur.SHOULD);

				/* query string */
				// Query q = qp.parse(searchString);

				/* search into the index */
				searcher.search(inBoolQuery, collector);
				ScoreDoc[] hits = collector.topDocs().scoreDocs;

				// for (ScoreDoc h : hits) {
				// System.out.println(h.toString());
				// }
				//
				// System.out.println(hits.length);

				/* print results */
				System.out.println("Found " + hits.length + " hits.");
				for (int i = 0; i < hits.length; ++i) {
					int docId = hits[i].doc;
					Document d = searcher.doc(docId);
					String result = d.get("chiave") + "\t" +d.get("localita");
					//System.out.println("chiave: " + d.get("chiave") + "	 Lacalita: " + d.get("localita"));
					if (risultato.containsKey(result)) {
						int j = risultato.get(result).intValue();
						risultato.put(result, new Integer(j+1));
					} else {
						risultato.put(result, new Integer(1));
					}
				}
				
				int max = Collections.max(risultato.values());
				
				String localita = " ";
				
				for (Map.Entry<String, Integer> entri : risultato.entrySet()) {
					
					if(entri.getValue()==max){
						localita = entri.getKey();
					}
					
				}
				
				listaOutputs.add(entita + "\t" + localita);

			}
		}

		return listaOutputs;

	}

}