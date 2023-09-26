package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WikipediaRevisionParser {
//    public List<Revision> parse(InputStream testDataStream) throws IOException {
//        try {
//            JSONArray allRevisions = JsonPath.read(testDataStream, "$..allrevisions.*");
//            if(allRevisions != null ) {
//                int revisionsLimit = Math.min(allRevisions.size(), 13);
//                List<Revision> revisionList = new ArrayList<>(revisionsLimit);
//                List<String> revisionUser = JsonPath.read(allRevisions, "$..user");
//                System.out.println(revisionUser);
//                List<String> revisionTimestamp = JsonPath.read(allRevisions, "$..timestamp");
//                System.out.println(revisionTimestamp);
////                ByteArrayOutputStream baos = null;
//                for (int i = 0; i < revisionsLimit; i++) {
//                    Revision revisionHolder = new Revision(revisionUser.get(i), revisionTimestamp.get(i));
//                    revisionList.add(revisionHolder);
////                    baos = new ByteArrayOutputStream();
////                    for (String line : revisionUser) {
////                        baos.write(line.getBytes());
////                    }
////                }
////                byte[] bytes = baos.toByteArray();
//                }
//                System.out.println(revisionList);
//                return revisionList;
//            }else {
//                System.err.println("There is No article with that name!");
//            }
//        }
//        catch (IOException e){
//            System.err.println("Error! " + e.getMessage());
//        }
//        return null;
//    }

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

    public JSONArray timestampParser(String testDataStream) {
        JSONArray result = (JSONArray) JsonPath.read(testDataStream, "$..timestamp");
//        ArrayList<String> timestampArrayList = new ArrayList<>();
//        timestampArrayList.add(String.valueOf(result));
////        for (String data : timestampArrayList) {
////            for (int i = 0; i < timestampArrayList.size(); i++) {
////                timestampArrayList.add(data);
////            }
////        }
//        System.out.println(timestampArrayList);
        return result;
    }

    public JSONArray usernameParser(String testDataStream) {
        JSONArray result = (JSONArray) JsonPath.read(testDataStream, "$..username");
//        ArrayList<String> usernameArrayList= new ArrayList<>();
//        usernameArrayList.add(String.valueOf(result));
//        System.out.println(usernameArrayList);
        return result;
//        return result.get(0).toString(); // this is returning an empty string => the InputStream doesn't add anything into it because it's only usable once
    }

}