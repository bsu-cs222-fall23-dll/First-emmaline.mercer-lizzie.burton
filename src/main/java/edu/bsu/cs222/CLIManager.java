package edu.bsu.cs222;

import java.util.Scanner;

public class CLIManager {


        private WikipediaAPI wikipediaAPI;
        private WikipediaRevisionParser articleParser;

        public CLIManager() {
            wikipediaAPI = new WikipediaAPI();
            articleParser = new WikipediaRevisionParser()();
        }

        public void start() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Wikipedia article title:");

            String articleTitle = scanner.nextLine();

            if (articleTitle == null || articleTitle.trim().isEmpty()) {
                System.err.println("Please provide an article name.");
                System.exit(0);
            }

            try {
                String jsonData = wikipediaAPI.getArticleJson(articleTitle);
                Article article = articleParser.parseJson(jsonData);

                if (article.getRedirectedFrom() != null) {
                    System.out.println("Redirected to " + article.getTitle());
                }

                displayRevisions(article);

            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }

        private void displayRevisions(Article article) {
            for (Revisions revision : article.getRevisions()) {
                System.out.println(revision.getTimestamp() + " " + revision.getUsername());
            }
        }

        public static void main(String[] args) {
            CLIManager cliManager = new CLIManager();
            cliManager.start();
        }
    }

}
