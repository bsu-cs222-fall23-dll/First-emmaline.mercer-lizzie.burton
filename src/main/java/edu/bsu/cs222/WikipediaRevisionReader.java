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

    public static void main(String[] args) {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the article name you are looking for: ");
        String line = scanner.nextLine(); // takes user input

        try {
            String timestamp = revisionReader.getLatestRevisionOf(line);
            System.out.println(timestamp); // prints out the timestamp
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
            connection.setRequestProperty("User-Agent", "CS222FirstProject/0.1 (emmaline.mercer@bsu.edu)");
            InputStream inputStream = connection.getInputStream();
            WikipediaRevisionParser parser = new WikipediaRevisionParser();

            String timestamp = parser.timestampParser(inputStream);
            String username = parser.usernameParser(inputStream);

            return timestamp;
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException(malformedURLException);
        }
    }

}
