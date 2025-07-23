package com.example.demo.exception;

public class GitHubUserNotFoundException extends RuntimeException {
    public GitHubUserNotFoundException(String message) {
        super(message);
    }
}
