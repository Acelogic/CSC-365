
package Assignment1;

import SimilarityMetric.TFIDF;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;


public class Parser {

    private ArrayList<String> urlList;
    private ArrayList<String> wordList;
    private String url;


    public Parser(String url, ArrayList urlList) throws IOException {
        this.url = url;
        this.urlList = urlList;
        wordList = populateWordList();

    }

    //gets the words from the webpage div
    private ArrayList<String> populateWordList() throws IOException {
        Document webDoc = Jsoup.connect(url).userAgent("Mozilla").get();//List of words/terms
        Elements termsList = webDoc.select("div#mw-content-text");
        termsList.text();
        wordList = new ArrayList<>(Arrays.asList(termsList.text().split("[^a-zA-Z]+"))); // \\W+
        return wordList;
    }

   /* private void cache() throws IOException {
        Document doc = Jsoup.connect(url).get();
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter("d://test.txt"));
        writer.write(doc.text());
    }*/

    public HashTable getHashTable() throws IOException {

        ArrayList<Word> wordObjList = new ArrayList<>();
        HashTable ht = new HashTable();
        for (int i = 0; i < wordList.size(); i++) {
            wordObjList.add(new Word(wordList.get(i), new TFIDF(wordList, urlList).tfidf(wordList.get(i))));

            ht.put(wordObjList.get(i));
        }

        return ht;
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<String> getUrlList() {
        return urlList;
    }

}