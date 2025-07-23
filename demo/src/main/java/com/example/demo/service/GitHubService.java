package com.example.demo.service;

import com.example.demo.client.GitHubBranch;
import com.example.demo.client.GitHubRepo;
import com.example.demo.dto.BranchResponse;
import com.example.demo.dto.RepositoryResponse;
import com.example.demo.exception.GitHubUserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {

    public List<RepositoryResponse> getRepositories(String username) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String repoUrl = "https://api.github.com/users/" + username + "/repos";
            ResponseEntity<GitHubRepo[]> repoResponse = restTemplate.getForEntity(repoUrl, GitHubRepo[].class);
            GitHubRepo[] repos = repoResponse.getBody();

            if (repos == null || repos.length == 0) {
                throw new GitHubUserNotFoundException("No repositories found for user: " + username);
            }

            
            return Arrays.stream(repos)
                .filter(repo -> !repo.isFork()) 
                .map(repo -> {
                    
                    String branchUrl = "https://api.github.com/repos/" + username + "/" + repo.getName() + "/branches";
                    ResponseEntity<GitHubBranch[]> branchResponse = restTemplate.getForEntity(branchUrl, GitHubBranch[].class);
                    GitHubBranch[] branches = branchResponse.getBody();

                    List<BranchResponse> branchResponses = Arrays.stream(branches)
                        .map(branch -> {
                            BranchResponse b = new BranchResponse();
                            b.setName(branch.getName());
                            b.setLastCommitSha(branch.getCommit().getSha());
                            return b;
                        })
                        .collect(Collectors.toList());

                    RepositoryResponse response = new RepositoryResponse();
                    response.setRepositoryName(repo.getName());
                    response.setOwnerLogin(repo.getOwner().getLogin());
                    response.setBranches(branchResponses);
                    return response;
                })
                .collect(Collectors.toList());

        } catch (HttpClientErrorException.NotFound e) {
            throw new GitHubUserNotFoundException("User not found: " + username);
        }
    }
}
