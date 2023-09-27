package edu.bsu.cs222;

import java.io.IOException;
import java.util.Scanner;

public class CLIManager {

    public static void main(String[] args) {
        CLIManager manager = new CLIManager();
        manager.run();
    }

    public void run() {
        Revision revision = new Revision();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the article name you are looking for: ");
        String articleTitle = scanner.nextLine();
        checkArticleTitle(articleTitle);

        try {
            revision.printListOFAllRevisions(articleTitle);
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }

    public void checkArticleTitle(String userInput) {
        if (userInput.trim().isEmpty()) {
            System.err.println("Invalid input -- nothing was inputted.");
        }
    }
}
