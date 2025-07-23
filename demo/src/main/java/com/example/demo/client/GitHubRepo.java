package com.example.demo.client;

import lombok.Data;

@Data
public class GitHubRepo {
    private String name;
    private boolean fork;
    private GitHubOwner owner;
}
