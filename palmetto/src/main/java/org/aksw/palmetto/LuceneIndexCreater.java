package org.aksw.palmetto;
import org.aksw.palmetto.*;
import org.aksw.palmetto.corpus.lucene.creation.*;
import org.aksw.palmetto.corpus.lucene.creation.IndexableDocument;
import org.aksw.palmetto.corpus.lucene.creation.PositionStoringLuceneIndexCreator;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
public class LuceneIndexCreater{

    public static void main(String args[]){
        if(args.length < 2){
		System.out.println("Too few Arguments");
		return;
	}
        String indexDir = args[0];
        String filepath = args[1];
       // Iterator<IndexableDocument> docIterator = readDataLineByLine(filepath);
        PositionStoringLuceneIndexCreator creator = new PositionStoringLuceneIndexCreator(
        "text", "length");
       	createIndexSplitwise(filepath,new File(indexDir),creator);
    }
	public static void createIndexSplitwise(String file, File indexDir, PositionStoringLuceneIndexCreator creator){
		Path pathToFile = Paths.get(file);
		List<IndexableDocument> dir = new ArrayList<IndexableDocument>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"))){
			String line = br.readLine();
			while(line != null){
				if(dir.size() == 100000){
					System.out.println("CreatingIndex");
					creator.createIndex(indexDir, dir.iterator());
					dir = new ArrayList<IndexableDocument>();
				}
				if(line.equals("")){
					line = br.readLine();
					continue;
				}
				String arr[] = line.split(" ");
				int length = arr.length;
				IndexableDocument id = new IndexableDocument(line,length);
				dir.add(id);
				line = br.readLine();
			}
			creator.createIndex(indexDir, dir.iterator());
			LuceneIndexHistogramCreator histogramCreator = new LuceneIndexHistogramCreator("length");		
			histogramCreator.createLuceneIndexHistogram(indexDir.getPath());
		}catch(Exception e){
			System.out.println(e);
		}
	}


}
