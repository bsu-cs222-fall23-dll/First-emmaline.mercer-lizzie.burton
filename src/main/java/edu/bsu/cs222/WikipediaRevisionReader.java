package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

public class WikipediaRevisionReader {

/**
 * Reads the article name of a Wikipedia page
 * should one of the classes use that data to print it out?
 * or should we send it directly to updates?
 */

    public static void main(String[] args) throws IOException {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the article name you are looking for: ");
        String articleTitle = scanner.nextLine(); // takes user input

        try {
            String timestamp = revisionReader.getLatestRevisionOf(articleTitle);
            System.out.println(timestamp); // prints out the timestamp
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }

    private String getLatestRevisionOf(String articleTitle) throws IOException {
        URL url = createWorkingURL(articleTitle);

        try {
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "CS222FirstProject/0.1 (emmaline.mercer@bsu.edu)");
            // wiki etiquette

            InputStream inputStream = connection.getInputStream();
            WikipediaRevisionParser parser = new WikipediaRevisionParser();

            Object inputStreamData = inputStream;
            String timestamp = parser.timestampParser((String) inputStreamData);


            String username = parser.usernameParser((String) inputStreamData);

            return timestamp;

        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException(malformedURLException);
        }
    }

    public URL createWorkingURL(String articleTitle) throws MalformedURLException {
        String encodedURLString = URLEncoder.encode(articleTitle, Charset.defaultCharset());
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="
                + encodedURLString + "&rvprop=timestamp|user&rvlimit=13&redirects");
        return url;
    }

}
