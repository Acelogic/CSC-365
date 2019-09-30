package SimilarityMetric.Tests;

import Assignment1.DocumentList;
import Assignment1.HashTable;
import Assignment1.Parser;
import Assignment1.Word;
import SimilarityMetric.TFIDF;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class TermFrequencyTesting {
    public static void main(String[] args) throws IOException {
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://simple.wikipedia.org/wiki/Android_(operating_system)");
        urlList.add("https://simple.wikipedia.org/wiki/Android_(operating_system)");
     /*   urlList.add("https://simple.wikipedia.org/wiki/Apple_Inc.");
        urlList.add("https://simple.wikipedia.org/wiki/Nintendo");
        urlList.add("https://simple.wikipedia.org/wiki/Shave");
        urlList.add("https://simple.wikipedia.org/wiki/Mario");
        urlList.add("https://simple.wikipedia.org/wiki/Sonic_the_Hedgehog");
        urlList.add("https://simple.wikipedia.org/wiki/Linux");
        urlList.add("https://simple.wikipedia.org/wiki/Arch_Linux");
        urlList.add("https://simple.wikipedia.org/wiki/Microsoft_Windows");
        urlList.add("https://simple.wikipedia.org/wiki/IPhone");
        urlList.add("https://simple.wikipedia.org/wiki/Trigonometric_functions");
        urlList.add("https://simple.wikipedia.org/wiki/Calculus");
        urlList.add("https://simple.wikipedia.org/wiki/space");
        urlList.add("https://simple.wikipedia.org/wiki/Turtle");
        urlList.add("https://simple.wikipedia.org/wiki/Dog");
        urlList.add("https://simple.wikipedia.org/wiki/Cat");
        urlList.add("https://simple.wikipedia.org/wiki/Fish");
        urlList.add("https://simple.wikipedia.org/wiki/Lion");
        urlList.add("https://simple.wikipedia.org/wiki/Bird");*/


        Parser dListParser = new Parser(urlList);
        DocumentList<ArrayList<String>> dList = dListParser.getDocList();

        DecimalFormat df = new DecimalFormat("0");
        df.setMaximumFractionDigits(8);

        String term = "mario";

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