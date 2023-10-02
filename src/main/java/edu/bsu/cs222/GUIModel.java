package edu.bsu.cs222;

import net.minidev.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public class GUIModel {

    public JSONArray searchArticle(String inputName) throws IOException {
        WikipediaRevisionReader reader = new WikipediaRevisionReader();
        JSONArray revisedArray = reader.readParsedData(inputName);
        return revisedArray;
    }
}
