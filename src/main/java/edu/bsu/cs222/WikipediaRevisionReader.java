package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

public class WikipediaRevisionReader {
//    String input;
//    public WikipediaRevisionReader(String userInput) {
//        this.input = userInput;
//    }
//    public String getLatestRevisionOf(String articleTitle) throws IOException {
////        Revision revision = new Revision();
//        WikipediaRevisionReader reader = new WikipediaRevisionReader();
//        URL url = reader.getConstructedURL(articleTitle);
//        //prints correct URL
//        URLConnection connection = WikipediaRevisionReader.connectURLToWiki(url);
//        JSONArray data = (JSONArray) readParsedData(connection);
//        return data.toString();
//
////        System.out.println(new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset()));
////        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
//
////
//////        System.out.println(url); //printed correct url
////
////        try {
////
//////            System.out.println("print");
//////            System.out.println(data); // nothing printed
////            revision.printListOFAllRevisions(data);
////
//////            return data.toString();
////
////        } catch (MalformedURLException malformedURLException) {
////            throw new RuntimeException(malformedURLException);
////        }
//    }

    public JSONArray readParsedData(String articleTitle) throws IOException {
//        URLConnection connection = WikipediaRevisionReader.connectURLToWiki(url);
//        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        WikipediaRevisionReader reader = new WikipediaRevisionReader();
        Redirect redirect = new Redirect();
        URL url = reader.getConstructedURL(articleTitle);
        //prints correct URL
        URLConnection connection = WikipediaRevisionReader.connectURLToWiki(url);
        String inputStreamData = new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());

        redirect.isRedirected(inputStreamData);
        ifPageMissing(inputStreamData);

//        System.out.println(inputStreamData);

//        JSONArray timestamp = (parser.timestampParser(inputStreamData));
//        System.out.println(timestamp.get(0));
//        JSONArray username = (parser.usernameParser(inputStreamData));
//        System.out.println(username.get(0));
        JSONArray revisions = (parser.revisionsParser(inputStreamData));
        System.out.println(revisions.get(0));

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

//
//    public static byte[] getByteArray(URLConnection connection) throws IOException {
//        InputStream inputStream = null;
//        HttpURLConnection httpConnection = (HttpURLConnection) connection;
//        int responseCode = httpConnection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            inputStream = httpConnection.getInputStream();
//        }
//        byte[] inputStreamData = inputStreamToByte(inputStream);
//        return inputStreamData;
//    }
//
//    public static byte[] inputStreamToByte(InputStream inputStream) {
//        try {
//            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//            int character;
//            while ((character = inputStream.read()) != -1) {
//                byteStream.write(character);
//            }
//            byte imgdata[] = byteStream.toByteArray();
//            byteStream.close();
//            return imgdata;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
