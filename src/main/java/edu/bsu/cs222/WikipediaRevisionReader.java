package edu.bsu.cs222;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.Charset;

public class WikipediaRevisionReader {

    public String getLatestRevisionOf(String articleTitle) throws IOException {
        URL url = createWorkingURL(articleTitle);
        try {
            URLConnection connection = url.openConnection();
//
//            byte[] byteArray = getByteArray(connection);
//            System.out.println(byteArray);

            connection.setRequestProperty("User-Agent", "CS222FirstProject/0.1 (emmaline.mercer@bsu.edu)");
            JSONArray data = (JSONArray) readParsedData(connection);
            return data.toString();

        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException(malformedURLException);
        }
    }

    public JSONArray readParsedData(URLConnection connection) throws IOException {
//        InputStream inputStream = new ByteArrayOutputStream(bytes);
        InputStream inputStream = connection.getInputStream();
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
//        return parser.parse(inputStream);
//        String inputStreamData = inputStream.toString();
        String inputStreamData = inputStream.toString();
//        List parseAllRevisions = parser.parse((InputStream) inputStreamData);

        JSONArray timestamp = (parser.timestampParser(inputStreamData.toString()));
        JSONArray username = (parser.usernameParser(inputStreamData.toString()));
        JSONArray revisions = (parser.revisionsParser(inputStreamData.toString()));

        JSONArray allData = new JSONArray();
        allData.add(timestamp);
        allData.add(username);
        allData.add(revisions);
        return allData;

    }

    public URL createWorkingURL(String articleTitle) throws MalformedURLException {
        String encodedURLString = URLEncoder.encode(articleTitle, Charset.defaultCharset());
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="
                + encodedURLString + "&rvprop=timestamp|user&rvlimit=13&redirects");
        return url;
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
