package edu.bsu.cs222;

import java.io.IOException;
import java.util.List;

public class GUIModel {

    private final WikipediaRevisionReader reader;
    private final WikipediaRevisionParser parser;

    public GUIModel() {
        this.reader = new WikipediaRevisionReader();
        this.parser = new WikipediaRevisionParser();
    }



    public List<Revision> getRevisionsForArticle(String articleTitle) throws IOException {
        String jsonData = reader.fetchRevisionData(articleTitle);
        String redirectedTitle = Redirect.getRedirectedTitle(jsonData);


        if (redirectedTitle != null) {
            // If there's a redirection, fetch the revision data again for the new title
            jsonData = reader.fetchRevisionData(redirectedTitle);
        }

        return parser.parseRevisions(jsonData);
    }


}
