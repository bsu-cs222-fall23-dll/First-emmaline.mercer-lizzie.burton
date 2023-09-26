package edu.bsu.cs222;

import java.io.IOException;
import java.util.Scanner;

public class CLIManager {

    public static void main(String[] args) {
        CLIManager manager = new CLIManager();
        manager.run();
    }

    public void run() {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the article name you are looking for: ");
        String articleTitle = scanner.nextLine();

        try {
            String timestamp = revisionReader.getLatestRevisionOf(articleTitle);
            System.out.println(timestamp);
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }
}
