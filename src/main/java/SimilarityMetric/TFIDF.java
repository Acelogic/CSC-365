package SimilarityMetric;

import java.util.ArrayList;


import java.io.IOException;
import java.util.Hashtable;
import java.util.List;


public class TFIDF {

    // Instance Variables
    private ArrayList<String> doc;
    private ArrayList<String> dList;
    private int docsContainingTerm;
    private int wordFrequency;
    private boolean containsTerm = false;


    // Constructor
    public TFIDF(ArrayList<String> wordList, ArrayList<String> urlList) {
        this.doc = wordList;
        this.dList = urlList;
    }

    public double tf(String word) {
        int counter = 0;
        for (String term : doc) {
            //  System.out.println(term);
            if (term.equalsIgnoreCase(word)) {
                counter++;
            }
        }
        wordFrequency = counter;
        double tfAnswer = (double) wordFrequency / (double) doc.size();

        return tfAnswer;
    }

    public double idf(String word) throws IOException{
        SharedWords sw= new SharedWords(dList);
        docsContainingTerm = sw.getDocsContainingTerm(word);

        return Math.log10((double) dList.size() / (double) docsContainingTerm);
    }


    public int getDocsContainingTerm() {
        return docsContainingTerm;
    }

    public int getWordFrequency() {
        return wordFrequency;
    }

    public boolean getContainsTerm() {
        return containsTerm;
    }

    public int getDocSize(){
        return doc.size();
    }

    public int getDListSize(){
        return dList.size();
    }

    public double tfidf(String term) throws IOException {
        double tfidfResult = tf(term) * idf(term);
        if (tfidfResult < 0) {
            throw new IOException("Something is wrong with maths");
        } else {
            return tf(term) * idf(term);
        }
    }
}