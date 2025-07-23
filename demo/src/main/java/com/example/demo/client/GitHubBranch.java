package com.example.demo.client;

import lombok.Data;

@Data
public class GitHubBranch {
    private String name;
    private GitHubCommit commit;
}
