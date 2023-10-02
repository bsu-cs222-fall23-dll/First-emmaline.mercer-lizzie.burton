package edu.bsu.cs222;

import net.minidev.json.JSONArray;

import java.io.IOException;

public class GUIModel {

    public JSONArray searchArticle(String inputName) {
        WikipediaRevisionReader reader = new WikipediaRevisionReader();
        JSONArray revisedList = reader.readParsedData(inputName);
        return revisedList;
    }
}
