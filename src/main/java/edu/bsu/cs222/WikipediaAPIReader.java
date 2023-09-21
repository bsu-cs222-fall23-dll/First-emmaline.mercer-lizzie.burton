package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

public class WikipediaAPIReader {

/**
 * Reads the article name of a Wikipedia page
 * should one of the classes use that data to print it out?
 * or should we send it directly to updates?
 */

    public static void main(String[] args) {
        WikipediaAPIReader revisionReader = new WikipediaAPIReader();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        try {
            String timestamp = revisionReader.getLatestRevisionOf(line);
            System.out.println(timestamp);
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }

    private String getLatestRevisionOf(String articleTitle) throws IOException {
        String urlString = String.format("", articleTitle);
        String encodedURLString = URLEncoder.encode(urlString, Charset.defaultCharset());
        try {
            URL url = new URL(encodedURLString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "WikipediaRevisionReader/0.1 ()");
            InputStream inputStream = connection.getInputStream();
            WikipediaRevisionParser parser = new WikipediaRevisionParser();
            String timestamp = parser.parser(inputStream);
            return timestamp;
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException(malformedURLException);
        }
    }

}