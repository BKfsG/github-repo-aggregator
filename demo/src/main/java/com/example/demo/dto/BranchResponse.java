package com.example.demo.dto;

import lombok.Data;

@Data
public class BranchResponse {
    private String name;
    private String lastCommitSha;
}
