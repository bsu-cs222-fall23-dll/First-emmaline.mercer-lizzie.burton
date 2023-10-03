package edu.bsu.cs222;

import java.util.List;

public class Article {
    private String title;

    private List<Revision> revisions;

    public Article(String title, List<Revision> revisions) {
        this.title = title;

        this.revisions = revisions;
    }


    public Revision getLatestRevision() {
        return revisions.get(0);
    }


}
