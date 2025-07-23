package com.example.demo.dto;

import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class RepositoryResponse {

    @JsonProperty("name")
    private String repositoryName;

    @JsonProperty("ownerLogin")
    private String ownerLogin;

    private List<BranchResponse> branches;
}
