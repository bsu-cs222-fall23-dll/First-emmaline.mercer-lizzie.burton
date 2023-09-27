package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPathException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CLIManager {

    public static void main(String[] args) {
        CLIManager manager = new CLIManager();
        manager.run();
    }

    public void run() {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        Revision revision = new Revision();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the article name you are looking for: ");
        String articleTitle = scanner.nextLine();
        checkArticleTitle(articleTitle);


        try {
            if (articleTitle == null) {
                System.err.println("No article title provided - exiting program.");
//                System.exit(0); //1???

//            } else if (articleTitle  ) {
//
            } else if (articleTitle != null ) {
//                String inputStreamData = String.valueOf(revisionReader.readParsedData(articleTitle));
//                System.out.println(inputStreamData);
                revision.printListOFAllRevisions(articleTitle);
//                allDataRevisions(inputStreamData);
            }
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }

    public void checkArticleTitle(String userInput) {
        if (userInput.trim().equals("")) {
            System.err.println("Invalid input -- nothing was inputed.");
        }
    }
//    public void allDataRevisions(String inputStreamData) {
//        WikipediaRevisionParser parser = new WikipediaRevisionParser();
//        Revision revision = new Revision();
//
//        JSONArray timestamp = (parser.timestampParser(inputStreamData));
//        JSONArray username = (parser.usernameParser(inputStreamData));
//
////        revision.printListOFAllRevisions(inputStreamData);
//    }

//    public JSONArray convertStringtoJSONArray(String inputStreamData) throws JsonPathException {
//        String data = inputStreamData;
//        JSONArray jsonArr = new JSONArray(data);
//
//        for (int i = 0; i < jsonArr.length(); i++)
//        {
//            JSONObject jsonObj = jsonArr.getJSONObject(i);
//
//            System.out.println(jsonObj);
//        }
//    }
}
