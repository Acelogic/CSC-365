import java.util.*;

public class TermFrequencyTesting {
    public static void main(String[] args) {

        ArrayList<String> d1 = new ArrayList<>();
        d1.add("this");
        d1.add("is");
        d1.add("a");
        d1.add("a");
        d1.add("sample");

        ArrayList<String> d2 = new ArrayList<>();
        d2.add("this");
        d2.add("is");
        d2.add("another");
        d2.add("another");
        d2.add("example");
        d2.add("example");
        d2.add("example");



        ArrayList<ArrayList<String>> dList = new ArrayList<>();
        dList.add(d1);
        dList.add(d2);

        TFIDF inst1 = new TFIDF(d1, dList);
        TFIDF inst2 = new TFIDF(d2, dList);


        // tf calculation
        System.out.println("--------------------------");
        System.out.println("TF CALCULATION");
        System.out.println(inst1.calcTF("example"));
        System.out.println(inst2.calcTF("example"));
        System.out.println("--------------------------");

        // idf calculation
        System.out.println("IDF CALCULATION");
        System.out.println(inst1.calcIDF("example"));
        System.out.println(inst2.calcIDF("example"));
        System.out.println("--------------------------");

        //tfidf calculation
        System.out.println("TFIDF CALCULATION");
        System.out.println(inst1.calcTFIDF("example"));
        System.out.println(inst2.calcTFIDF("example"));


    }
}
