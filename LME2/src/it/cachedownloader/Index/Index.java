package it.cachedownloader.Index;

import it.cachedownloader.lme2.LocationParser.LocationParser;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.List;

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
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Index {

	public static final LocationParser parser = new LocationParser();

	public static void main(String[] args) throws IOException, ParseException {

		//creaIndex();

		searchIndex("Rome");

	}

	public static void searchIndex(String searchString) throws IOException,
			ParseException {
		System.out.println("Searching for '" + searchString + "'");

		StandardAnalyzer analyzer = new StandardAnalyzer(CharArraySet.EMPTY_SET);
		Directory directory = FSDirectory.open(FileSystems.getDefault()
				.getPath("index/indice"));

		/* set the maximum number of results */
		int maxHits = 1000;

		/* open a directory reader and create searcher and topdocs */
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(maxHits);

		/* create the query parser */
		QueryParser qp = new QueryParser("localita", analyzer);

		/* query string */
		Query q = qp.parse(searchString);

		/* search into the index */
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;

		for (ScoreDoc h : hits) {

			System.out.println(h.toString());

		}
		

		System.out.println(hits.length);

	}

	public static void creaIndex() throws IOException {

		/* create a standard analyzer */
		StandardAnalyzer analyzer = new StandardAnalyzer(CharArraySet.EMPTY_SET);

		/* create the index in the pathToFolder or in RAM (choose one) */
		Directory directory = FSDirectory.open(FileSystems.getDefault()
				.getPath("index/indice"));

		/* set an index congif */
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		config.setOpenMode(OpenMode.CREATE);

		/* create the writer */
		IndexWriter writer = new IndexWriter(directory, config);

		List<String> listaLocation = parser.listaLocation();
		for (String localitaSporca : listaLocation) {
			String[] localitaSplitted = localitaSporca.split("\t");
			String chiave = localitaSplitted[1];
			String localita = localitaSplitted[0];

			Document doc = new Document();
			doc.add(new StringField("chiave", chiave, Field.Store.YES));
			doc.add(new TextField("localita", localita, Field.Store.YES));

			writer.addDocument(doc);

		}

		/* close the writer */
		writer.close();

	}

}