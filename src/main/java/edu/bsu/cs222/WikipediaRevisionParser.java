package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class WikipediaRevisionParser {
    public JSONArray revisionsParser(String testDataStream) throws IOException {
        try {
            JSONArray revisions = JsonPath.read(testDataStream, "$.query.pages.*.revisions[*]");

            if (revisions != null) {
                int lengthLimit = Math.min(revisions.size(), 13);
                JSONArray theFirst13Revisions = new JSONArray();
                for (int i = 0; i < lengthLimit; i++) {
                    theFirst13Revisions.add(revisions.get(i));
                }
                return theFirst13Revisions;
            } else {
                throw new IOException("No revisions found in JSON data");
            }
        } catch (Exception e) {
            throw new IOException("Error parsing JSON response: " + e.getMessage());
        }
    }

//    public void missingPage(Object )

    public JSONArray timestampParser(String testDataStream) {
        JSONArray result = (JSONArray) JsonPath.read(testDataStream, "$..timestamp");
        return result;

//        ArrayList<String> timestampArrayList = new ArrayList<>();
//        timestampArrayList.add(String.valueOf(result));
////        for (String data : timestampArrayList) {
////            for (int i = 0; i < timestampArrayList.size(); i++) {
////                timestampArrayList.add(data);
////            }
////        }
//        System.out.println(timestampArrayList);
    }

    public JSONArray usernameParser(String testDataStream) {
        JSONArray result = (JSONArray) JsonPath.read(testDataStream, "$..user");
        return result;


//        ArrayList<String> usernameArrayList= new ArrayList<>();
//        usernameArrayList.add(String.valueOf(result));
//        System.out.println(usernameArrayList);
//        return result.get(0).toString(); // this is returning an empty string => the InputStream doesn't add anything into it because it's only usable once
    }
//
//    public JSONArray dataInputStream() {
//        WikipediaRevisionReader reader = new WikipediaRevisionReader();
//        WikipediaRevisionParser parser = new WikipediaRevisionParser();
////        String inputStreamData = reader.readParsedData(connection);
//        System.out.println(inputStreamData); // INPUTSTREAMDATA => has all the data from the website!!
//
////        List parseAllRevisions = parser.parse((InputStream) inputStreamData);
//
//        String newInputStreamData = inputStreamData;
//
//        JSONArray username = (parser.usernameParser(newInputStreamData));
//        System.out.println(username);
//        JSONArray timestamp = (parser.timestampParser(newInputStreamData));
//        System.out.println(timestamp.get(0));
//        JSONArray revisions = (parser.revisionsParser(newInputStreamData));
//
//        JSONArray allData = new JSONArray();
//        allData.add(timestamp);
//        allData.add(username);
//        allData.add(revisions);
//
//        return allData;
//    }

}