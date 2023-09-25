package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private String articleTitle;
    private List<Revisions> revisions;
    private String redirectedFrom;

    public Article(String articleTitle, List<Revisions> revisions, String redirectedFrom) {
        this.revisions = new ArrayList<>();

    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getRedirectedFrom() {
        return redirectedFrom;
    }

    public List<Revisions> getRevisions() {
        return revisions;
    }


    public void addRevision (Revisions revision) {
        Revisions Revision;
        this.revisions.add(revision);
    }

    public void setRedirectedFrom(String correctTitle) {
        this.redirectedFrom = correctTitle;
    }
}
