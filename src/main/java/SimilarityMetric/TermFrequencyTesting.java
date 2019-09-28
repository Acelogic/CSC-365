package SimilarityMetric;

import Assignment1.Parser;

import java.io.IOException;
import java.util.*;

public class TermFrequencyTesting {
    public static void main(String[] args) throws IOException {
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://simple.wikipedia.org/wiki/Google");
        urlList.add("https://simple.wikipedia.org/wiki/Android_(operating_system)");
        urlList.add("https://simple.wikipedia.org/wiki/Apple_Inc.");


        Parser p0 = new Parser(urlList.get(0), urlList);
        Parser p1 = new Parser(urlList.get(1), urlList);
        Parser p2 = new Parser(urlList.get(2), urlList);

        ArrayList<String> wordList0 = p0.getWordList();
        ArrayList<String> wordList1 = p1.getWordList();
        ArrayList<String> wordList2 = p2.getWordList();

        TFIDF metric0 = new TFIDF(wordList0, urlList);
        TFIDF metric1 = new TFIDF(wordList1, urlList);
        TFIDF metric2 = new TFIDF(wordList2, urlList);

        String term = "Google";

        System.out.println("tf: " + metric0.tf(term));
        System.out.println("idf: " + metric0.idf(term));
        System.out.println("tfidf: "+ metric0.tfidf(term));
        System.out.println("Does Doc Contain Term: " + metric0.getContainsTerm());
        System.out.println("Docs Containing Term: " + metric0.getDocsContainingTerm());
        System.out.println("Word Freq: " + metric0.getWordFrequency());
        System.out.println("Doc Size: " + metric0.getDocSize());
        System.out.println("Doc List Size: " + metric0.getDListSize());

        System.out.println("===== Metric 2 ===");
        System.out.println("tf: " + metric1.tf(term));
        System.out.println("idf: " + metric1.idf(term));
        System.out.println("tfidf: "+ metric1.tfidf(term));
        System.out.println("Does Doc Contain Term: " + metric1.getContainsTerm());
        System.out.println("Docs Containing Term: " + metric1.getDocsContainingTerm());
        System.out.println("Word Freq: " + metric1.getWordFrequency());
        System.out.println("Doc Size: " + metric1.getDocSize());
        System.out.println("Doc List Size: " + metric1.getDListSize());


        System.out.println("===== Metric 3 ===");
        System.out.println("tf: " + metric2.tf(term));
        System.out.println("idf: " + metric2.idf(term));
        System.out.println("tfidf: "+ metric2.tfidf(term));
        System.out.println("Does Doc Contain Term: " + metric2.getContainsTerm());
        System.out.println("Docs Containing Term: " + metric2.getDocsContainingTerm());
        System.out.println("Word Freq: " + metric2.getWordFrequency());
        System.out.println("Doc Size: " + metric2.getDocSize());
        System.out.println("Doc List Size: " + metric2.getDListSize());

    }

}