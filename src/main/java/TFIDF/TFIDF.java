package TFIDF;

import java.util.ArrayList;


public class TFIDF {

    private ArrayList<String> document;
    public static ArrayList<ArrayList<String>> documentList;

    public TFIDF(ArrayList<String> document, ArrayList<ArrayList<String>> documentList) {
        this.document = document;
        this.documentList = documentList;


    }

    public double calcTF(String term) {
        int termFrequency = 0;
        for (String documentTerm : document) {
            if (term.equalsIgnoreCase(documentTerm)) {
                termFrequency++;
            }
        }
        return (double) termFrequency / (double) document.size();
    }

    public double calcIDF(String term) {
        double docsContainingTerm = 0;
        for (ArrayList<String> document : documentList) {
            for (String documentTerm : document) {
                if (term.equalsIgnoreCase(documentTerm)) {
                        docsContainingTerm++;
                        break;
                    }
                }
            }
        return Math.log10( documentList.size() / docsContainingTerm );
    }

    public double calcTFIDF(String term) {
        return calcTF(term) * calcIDF(term);
    }

}
