package SimilarityMetric;

import Assignment1.DocumentList;
import Assignment1.HashTable;
import Assignment1.Parser;
import Assignment1.Word;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class TermFrequencyTesting {
    public static void main(String[] args) throws IOException {
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://simple.wikipedia.org/wiki/Google");
        urlList.add("https://simple.wikipedia.org/wiki/Android_(operating_system)");
        urlList.add("https://simple.wikipedia.org/wiki/Apple_Inc.");


        Parser dListParser = new Parser(urlList);
        DocumentList<ArrayList<String>> dList = dListParser.getDocList();

        DecimalFormat df = new DecimalFormat("0");
        df.setMaximumFractionDigits(8);

        String term = "apple";

        for (int i = 0; i < urlList.size(); i++) {
            System.out.println("===== Metric " + (i+1) + " ===");
            System.out.println(urlList.get(i));
            Parser parser = new Parser(urlList.get(i));
            TFIDF metric = new TFIDF(parser.getWordList(), dList);
            System.out.println("tf: " + df.format(metric.tf(term)));
            System.out.println("idf: " + df.format(metric.idf(term)));
            System.out.println("tfidf: " + df.format(metric.tfidf(term)));
            System.out.println("Docs Containing Term: " + df.format(metric.getDocsContainingTerm()));
            System.out.println("Word Freq: " + df.format(metric.getWordFrequency()));
            System.out.println("Doc Size: " + df.format(metric.getDocSize()));
            System.out.println("Doc List Size: " + df.format(metric.getDListSize()));

        }
    }

}