package Assignment1;



import SimilarityMetric.CosineSimilarity;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class ParserTesting {
    public static void main(String[] args) throws IOException {
        Instant start = Instant.now();
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("https://simple.wikipedia.org/wiki/Google");
        urlList.add("https://simple.wikipedia.org/wiki/Android_(operating_system)");
        //urlList.add("https://simple.wikipedia.org/wiki/Google");
        urlList.add("https://simple.wikipedia.org/wiki/Apple_Inc.");
        //urlList.add("https://en.wikipedia.org/wiki/Google");
        //urlList.add("https://en.wikipedia.org/wiki/Mario");
        // urlList.add("https://en.wikipedia.org/wiki/Sonic_the_Hedgehog");

        Parser[] pArr = new Parser[urlList.size()];
        HashTable[] htArr = new HashTable[urlList.size()];

        ArrayList<Double> vectorA = new ArrayList<>();
        ArrayList<Double> vectorB = new ArrayList<>();

        for (int i = 0; i < urlList.size(); i++) {
            pArr[i] = new Parser(urlList.get(i), urlList);
            htArr[i] = pArr[i].getHashTable();
        }

        for (int i = 0; i < pArr[0].getWordList().size(); i++) {
            vectorA.add(htArr[0].get(pArr[0].getWordList().get(i)));

        }
        for (int i = 0; i < pArr[1].getWordList().size(); i++) {
            vectorB.add(htArr[1].get(pArr[1].getWordList().get(i)));

        }

        CosineSimilarity cosineSimilarity = new CosineSimilarity(vectorA, vectorB);
        System.out.println("cosineSimilarity:  " + cosineSimilarity.similarity());


        Instant end = Instant.now();
        Duration interval = Duration.between(start, end);
        System.out.println("Execution time: " + interval.getSeconds());


/*      int counter = 0;
        String term = "Apple";
        SharedWords<ArrayList<String>> listOfArraysList = new SharedWords<>();
        int parserIndex = 0;
        while(parserIndex < pArr.length) {
            listOfArraysList.add(pArr[parserIndex].getWordList());

            for (int i = 0; i < listOfArraysList.get(parserIndex).size(); i++) {
                if (term.equalsIgnoreCase(listOfArraysList.get(parserIndex).get(i))) {
                    counter++;
                    break;

                }
            }

            parserIndex++;
        }

        System.out.println(counter);
    }*/
    }
}