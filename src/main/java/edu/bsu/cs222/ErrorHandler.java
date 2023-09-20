package edu.bsu.cs222;

public class ErrorHandler {
    private class ArticleNotFoundException extends Throwable {
        public ArticleNotFoundException(String s) {
        }
    }
    private class NetworkErrorException extends Throwable {
        public NetworkErrorException(String networkErrorOccurred) {
        }
    }
}
//