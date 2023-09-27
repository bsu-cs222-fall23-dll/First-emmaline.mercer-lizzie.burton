package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

public class WikipediaRevisionReader {
    public JSONArray readParsedData(String articleTitle) throws IOException {
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        WikipediaRevisionReader reader = new WikipediaRevisionReader();
        Redirect redirect = new Redirect();
        URL url = reader.getConstructedURL(articleTitle);
        URLConnection connection = WikipediaRevisionReader.connectURLToWiki(url);
        String inputStreamData = new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
        redirect.isRedirected(inputStreamData);
        ifPageMissing(inputStreamData);
        JSONArray revisions = (parser.revisionsParser(inputStreamData));
        return revisions;
    }

    private void ifPageMissing(Object inputStreamData) {
        JSONArray pageMissingCheck  = JsonPath.read(inputStreamData, "$..missing");
        if( !pageMissingCheck.isEmpty()) {
            System.err.println("Error, No Page Found!");
        }
    }

    public URL getConstructedURL(String articleTitle) throws MalformedURLException {
        String encodedURLString = URLEncoder.encode(articleTitle, Charset.defaultCharset());
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="
                + encodedURLString + "&rvprop=timestamp|user&rvlimit=13&redirects");
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
