package Assignment1;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class Parser {
    public static String grabWebPage() throws IOException {
        Document webDoc = Jsoup.connect("https://en.wikipedia.org/wiki/Steve_Jobs").userAgent("Mozilla").data("name", "jsoup").get();
        Elements webElements = webDoc.select("div#mw-content-text");
        //This Makes the out.file (But we need to read from a control file)
        //PrintStream fileOut = new PrintStream(new File("control.txt"));
        //System.setOut(fileOut);
        String elements = null;
        for (Element el : webElements) {
            elements = el.text();
        }
        return elements;
    }

    public static void main(String[] args) {
        try {
            System.out.println(grabWebPage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}