package edu.bsu.cs222;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public class GUIModel {

    public ArrayList<String> searchArticle(String inputName) throws IOException {
        WikipediaRevisionReader reader = new WikipediaRevisionReader();
        ArrayList<String> revisedList = reader.readParsedData(inputName);
        return revisedList;
    }
}
