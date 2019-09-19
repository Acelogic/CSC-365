package Assignment1;

import SimilarityMetric.TFIDF;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;


public class Parser {

    public Parser() {


    }

    public ArrayList<String> getWebPage(String urls) throws IOException {
        Document webDoc = Jsoup.connect(urls).userAgent("Mozilla").get();//List of words/terms

        Elements termsList = webDoc.select("div#mw-content-text");
        ArrayList<String> words = null;
        for (Element e : termsList) {
            String regularExpression = "\\W+";  //* \\s+ *//*
            words = new ArrayList<>(Arrays.asList(e.text().split(regularExpression)));
        }
        return words;


    }


    // Returns TFIDF for word in documents
    public Hashtable<String, Hashtable<String,Double>> getTFIDF(ArrayList<String> urlList, String term) throws IOException {
        SimilarityMetric.TFIDF tfidfCalculator;

        Hashtable<String, ArrayList<String>> Doc_Table = new Hashtable<>();
        Hashtable<String, Double> TFIDF_Table = new Hashtable<>();

        Hashtable<String, Hashtable<String, Double >> finalTable = new Hashtable<>();


        for (String url : urlList) {
            // getWebPage(url) -> ArrayList<String>
            tfidfCalculator = new SimilarityMetric.TFIDF(getWebPage(url), urlList);

            //Debug
            /*
            System.out.println("The Term: " + "(" + term + ")" + " in -> " + url);
            System.out.println("TF: " + tfidfCalculator.tf(term));
            System.out.println("IDF: " + tfidfCalculator.idf(term));
            System.out.println("TFIDF: " + tfidfCalculator.tfidf(term));
            System.out.println("Word Frequency: " + tfidfCalculator.getWordFrequency());
            System.out.println("isTermContained?: " + tfidfCalculator.getContainsTerm());
            System.out.println("Docs Containing Term: " + tfidfCalculator.getDocsContainingTerm());
            System.out.println("----------------------------------------------");
*/
            // Populating HashTables

            //Maps Actual Links with Arrays of words corresponding to the link  <String htmlLinks, ArrayList<String> words>
            Doc_Table.put(url, getWebPage(url));   // www , list.get(0)

            // For each String inside of the Value of the Hashtable holding array
            for (String e : Doc_Table.get(url)) {
                tfidfCalculator.tfidf(e);
                TFIDF_Table.put(e, tfidfCalculator.tfidf(e));

                //
                finalTable.put(url, TFIDF_Table);
            }



        }
        return finalTable;
    }
}



