package edu.bsu.cs222;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WikipediaAPI {
    private static final String API_ENDPOINT =
            "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&rvlimit=13&formart=json&titles=";

    public  String getArticleJSON(String articleName) throws Exception {
        URL url = new URL(API_ENDPOINT + articleName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "WikipediaArticleTracker/0.1.0");

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        return response.toString();
    }

}