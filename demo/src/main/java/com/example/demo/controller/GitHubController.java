package com.example.demo.controller;

import com.example.demo.dto.RepositoryResponse;
import com.example.demo.exception.GitHubUserNotFoundException;
import com.example.demo.service.GitHubService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/{username}/repositories")
    public List<RepositoryResponse> getRepositories(@PathVariable String username) {
        return gitHubService.getRepositories(username);
    }

}
