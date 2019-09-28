package SimilarityMetric;

import Assignment1.Parser;

import java.io.IOException;
import java.util.ArrayList;

public class SharedWords {
    Parser[] pArr;
    SharedWordsAbstraction<ArrayList<String>> listOfArraysList = new SharedWordsAbstraction<>();
    int counter;

    public SharedWords(ArrayList<String> urlList) throws IOException {
        pArr = new Parser[urlList.size()];
        for (int i = 0; i < urlList.size(); i++) {
            pArr[i] = new Parser(urlList.get(i), urlList);
        }
    }

    public int getDocsContainingTerm(String term) {
        counter = 0;
        int parserIndex = 0;
        while (parserIndex < pArr.length) {
            listOfArraysList.add(pArr[parserIndex].getWordList());
            for (int i = 0; i < listOfArraysList.get(parserIndex).size(); i++) {
                if (term.equalsIgnoreCase(listOfArraysList.get(parserIndex).get(i))) {
                    counter++;
                    break;
                }
            }
            parserIndex++;
        }

        return counter;
    }
}

