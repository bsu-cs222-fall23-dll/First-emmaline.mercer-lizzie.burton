package edu.bsu.cs222;

public class CLManager {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("No article name provided.");
            System.exit(1);
        }

        String articleName = args[0];
        WikipediaAPI wikipediaAPI = new WikipediaAPI();
        String jsonData = wikipediaAPI.getArticleJSON(articleName);

        ArticleParser articleParser = new ArticleParser();
        Article article = articleParser.parseJson(jsonData);

        displayRevisions(article);
    }

    public static void displayRevisions(Article article) {
        if (article.getRedirectedTitle() != null) {
            System.out.println("Redirected to " + article.getRedirectedTitle());
        }

        for (Revision revision : article.getRevisions()) {
            System.out.println(revision.getTimeStamp() + " " + revision.getUserName());
        }
    }


}
