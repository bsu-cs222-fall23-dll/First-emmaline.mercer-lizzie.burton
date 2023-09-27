package edu.bsu.cs222;

import java.util.List;

public record Article(String title, List<Revision> revisions) {

    public Revision getLatestRevision() {
        // Assuming the first revision in the list is the latest
        return revisions.get(0);
    }
}