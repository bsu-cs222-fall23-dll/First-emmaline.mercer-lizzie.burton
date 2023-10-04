package edu.bsu.cs222;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CLIManager {

    private final WikipediaRevisionReader reader;
    private final WikipediaRevisionParser parser;
    private final Scanner scanner;

    public CLIManager() {
        this.reader = new WikipediaRevisionReader();
        this.parser = new WikipediaRevisionParser();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Enter a Wikipedia article title (or type 'exit' to quit):");
            String articleTitle = scanner.nextLine();

            if ("exit".equalsIgnoreCase(articleTitle)) {
                System.out.println("Goodbye!");
                break;
            }

            processArticle(articleTitle);
        }

        scanner.close();
    }

    public void processArticle(String articleTitle) {
        if (articleTitle.trim().isEmpty()) {
            System.err.println("Invalid input -- nothing was inputted.");
            return;
        }

        try {
            String jsonData = reader.fetchRevisionData(articleTitle);
            if (jsonData.contains("\"missing\"")) {
                System.out.println("The given article title does not exist on Wikipedia.");
                return;
            }

            List<Revision> revisions = parser.parseRevisions(jsonData);

            displayRevisions(articleTitle, revisions);

        } catch (IOException e) {
            System.out.println("An error occurred while fetching or parsing data. Details: " + e.getMessage());
        }
    }


    public void checkArticleTitle(String userInput) {
        if (userInput.trim().isEmpty()) {
            System.err.println("Invalid input -- nothing was inputted.");
        }
    }

    private void displayRevisions(String articleTitle, List<Revision> revisions) {
        if (revisions.isEmpty()) {
            System.out.println("No revisions found for the given article title.");
        } else {
            System.out.println("Last 13 revisions for " + articleTitle + ":");
            for (Revision revision : revisions) {
                System.out.println("User: " + revision.getUsername() + ", Timestamp: " + revision.getTimestamp());
            }
        }
    }

    public static void main(String[] args) {
        new CLIManager().start();
    }
}
