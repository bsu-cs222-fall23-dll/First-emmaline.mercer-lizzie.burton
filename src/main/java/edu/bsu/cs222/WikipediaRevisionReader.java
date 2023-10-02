package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class WikipediaRevisionReader {
    public void run() {
        WikipediaRevisionParser revisions = new WikipediaRevisionParser();
        WikipediaRevisionReader reader = new WikipediaRevisionReader();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the article name you are looking for: ");

        String articleTitle = scanner.nextLine();
//        reader.checkArticleTitle(articleTitle);

        try {
            revisions.printListOFAllRevisions(articleTitle);
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }
    public ArrayList<String> readParsedData(String articleTitle) throws IOException {
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        WikipediaRevisionReader reader = new WikipediaRevisionReader();
//        Redirect redirect = new Redirect();
        URL url = reader.getConstructedURL(articleTitle);
        URLConnection connection = WikipediaRevisionReader.connectURLToWiki(url);
        String inputStreamData = new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
        ArrayList<String> revisions = (parser.revisionsParser(inputStreamData));
//        redirect.isRedirected(inputStreamData);
//        ifPageMissing(inputStreamData);
        return revisions;
    }

    private void ifPageMissing(Object inputStreamData) {
        JSONArray pageMissingCheck  = JsonPath.read(inputStreamData, "$..missing");
        if( !pageMissingCheck.isEmpty()) {
            System.err.println("Error, No Page Found!");
        }
    }

    public void checkArticleTitle(String userInput) {
        if (userInput.trim().isEmpty()) {
            System.err.println("Invalid input -- nothing was inputted.");
        }
    }

    public URL getConstructedURL(String articleTitle){
        String encodedURLString = URLEncoder.encode(articleTitle, Charset.defaultCharset());
        URL url = null;
        try {
            url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="
                    + encodedURLString + "&rvprop=timestamp|user&rvlimit=13&redirects");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url;
    }

    public static URLConnection connectURLToWiki(URL url) {
        try {
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "CS222FirstProject/0.1 (emmaline.mercer@bsu.edu)");
            connection.connect();
            return connection;
        } catch(Exception e) {
            System.err.println("NETWORK ERROR");
            System.exit(0);
            return null;
        }
    }
}
