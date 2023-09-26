package edu.bsu.cs222;

import java.util.List;

public class Article {
    private String title;
    private List<Revision> revisions;

    public Article(String title, List<Revision> revisions) {
        this.title = title;
        this.revisions = revisions;
    }

    public String getTitle() {
        return title;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public Revision getLatestRevision() {
        // Assuming the first revision in the list is the latest
        return revisions.get(0);
    }
}
