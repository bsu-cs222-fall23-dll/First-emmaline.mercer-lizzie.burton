package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

public class WikipediaRevisionReader {
    private static final String WIKIPEDIA_API_URL_TEMPLATE =
            "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=%s&rvlimit=13&rvprop=timestamp|user";


//    private static final String WIKIPEDIA_API_URL_TEMPLATE =
//            "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=%s&rvlimit=10&rvprop=timestamp|user";

    public String fetchRevisionData(String articleTitle) throws IOException {
        String formattedUrl = String.format(WIKIPEDIA_API_URL_TEMPLATE, URLEncoder.encode(articleTitle, Charset.forName("UTF-8")));
        HttpURLConnection connection = null;
        InputStream response = null;

        try {
            URL url = new URL(formattedUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "WikipediaRevisionReader/0.1"); //rename this into what we had
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new IOException("Failed to fetch data. HTTP response code: " + responseCode);
            }

            response = connection.getInputStream();
            Scanner scanner = new Scanner(response).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";

        } finally {
            if (response != null) {
                response.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
