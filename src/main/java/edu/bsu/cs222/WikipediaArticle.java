package edu.bsu.cs222;

import java.util.List;

public class WikipediaArticle {
    private String articleTitle;
    private List<Updates> updatesList;
    private boolean isRedirect;
    private String redirectTo;

    public WikipediaArticle(String articleTitle, List<Updates> updatesList, boolean isRedirect, String redirectTo) {
        this.articleTitle = articleTitle;
        this.updatesList = updatesList;
        this.isRedirect = isRedirect;
        this.redirectTo = redirectTo;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public List<Updates> getUpdatesList() {
        return updatesList;
    }

    public void setUpdatesList(List<Updates> updatesList) {
        this.updatesList = updatesList;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public String getRedirectTo() {
        return redirectTo;
    }

    public void setRedirectTo(String redirectTo) {
        this.redirectTo = redirectTo;
    }
}
//added