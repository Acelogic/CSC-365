package Assignment1;



import SimilarityMetric.CosineSimilarity;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Hashtable;

public class CosineTest {
    public static void main(String[] args) throws IOException {

        Instant start = Instant.now();
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://en.wikipedia.org/wiki/Google");
        urlList.add("https://en.wikipedia.org/wiki/Android_(operating_system)");
        urlList.add("https://en.wikipedia.org/wiki/Apple_Inc.");
        urlList.add("https://en.wikipedia.org/wiki/Nintendo");
        urlList.add("https://en.wikipedia.org/wiki/Doug_Lea");

        urlList.add("https://en.wikipedia.org/wiki/Mario");
        urlList.add("https://en.wikipedia.org/wiki/Sonic_the_Hedgehog");
        urlList.add("https://en.wikipedia.org/wiki/Linux");
        urlList.add("https://en.wikipedia.org/wiki/Arch_Linux");
        urlList.add("https://en.wikipedia.org/wiki/Microsoft_Windows");

        urlList.add("https://en.wikipedia.org/wiki/IPhone");
        urlList.add("https://en.wikipedia.org/wiki/Trigonometric_functions");
        urlList.add("https://en.wikipedia.org/wiki/Law_of_cosines");
        urlList.add("https://en.wikipedia.org/wiki/Cosine_similarity");
        urlList.add("https://en.wikipedia.org/wiki/Turtle");

        urlList.add("https://en.wikipedia.org/wiki/Dog");
        urlList.add("https://en.wikipedia.org/wiki/Cat");
        urlList.add("https://en.wikipedia.org/wiki/Fish");
        urlList.add("https://en.wikipedia.org/wiki/Lion");
        urlList.add("https://en.wikipedia.org/wiki/Bird");


        DecimalFormat df = new DecimalFormat("0");
        df.setMaximumFractionDigits(20);


        DocumentList<ArrayList<String>> dList = new Parser(urlList).getDocList();
        ArrayList<Parser> pArr = new ArrayList<>();
        ArrayList<HashTable> htArr = new ArrayList<>();

        ArrayList<Double> vectorA = new ArrayList<>();
        ArrayList<Double> vectorB = new ArrayList<>();

        for (int i = 0; i < urlList.size(); i++) {
            //Adding Parser Objects to array
            pArr.add(new Parser(urlList.get(i)));
            // Adding Hashtables to array
            htArr.add(new HTFactory(pArr.get(i), dList).getHashtable());
        }

/*

        for (int i = 0; i < pArr.get(0).getWordList().size(); i++) {
            vectorA.add(htArr.get(0).get(pArr.get(0).getWordList().get(i)));

        }
        for (int i = 0; i < pArr.get(1).getWordList().size(); i++) {
            vectorB.add(htArr.get(1).get(pArr.get(1).getWordList().get(i)));


            CosineSimilarity cosineSimilarity = new CosineSimilarity(vectorA, vectorB);
            System.out.println("cosineSimilarity:  " + cosineSimilarity.similarity());

*/


         /*   // Test
     Parser parser  = new Parser(urlList.get(0));

     HashTable ht = new HTFactory(parser, dList).getHashtable();

     for(String key: parser.getWordList()) {
         System.out.println("Key: "  +key+ " ----> " +df.format(ht.get(key)));
     }*/


            Instant end = Instant.now();
            Duration interval = Duration.between(start, end);
            System.out.println("Execution time: " + interval.getSeconds());

        }

    }

