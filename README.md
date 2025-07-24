# GitHub Repository Aggregator

This application provides a REST API that retrieves public repositories for a given GitHub user. Forked repositories are excluded from the response. For each repository, the API returns basic metadata as well as a list of branches and the latest commit SHA associated with each branch.

---

## Technologies

- Java 21
- Spring Boot 3.5
- Spring Web MVC
- Jackson
- MockMvc (integration testing)
- GitHub REST API v3

---

## API Endpoint

`GET /api/github/{username}/repositories`

Returns all non-fork public repositories for the given GitHub username.

### Response Fields

- `name` – name of the repository  
- `ownerLogin` – login of the repository owner  
- `branches` – list of branches, each containing:
  - `name` – branch name  
  - `lastCommitSha` – SHA of the latest commit

---

### Sample Response

```json
[
  {
    "name": "Hello-World",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d"
      },
      {
        "name": "feature-1",
        "lastCommitSha": "b1b3f9723831141a31a1a7252a213e216ea76e56"
      }
    ]
  }
]
```

Error Handling
If the GitHub user does not exist, the API returns a structured error response with status 404:

```json
{
  "status": 404,
  "message": "User not found: {username}"
}
```

Exceptions are handled using a global controller advice class.

Integration Test
A single integration test is included to verify expected behavior for an existing GitHub user. The test covers:

- Successful invocation of the endpoint
- Correct exclusion of forked repositories
- Validation of repository structure and branch data
- Mocks are avoided unless technically required.

Setup Instructions
Ensure you have Java 21 and Maven installed.

Clone the repository:

git clone git clone https://github.com/BKfsG/github-repo-aggregator.git

Navigate to the project folder and run:

mvn spring-boot:run
Use the following request to query the API:

GET http://localhost:8080/api/github/octocat/repositories
To execute tests:

mvn test
Task Compliance Summary
Requirement	Status
Java 21 / Spring Boot 3.5	✅
WebFlux not used	✅
Single integration test included	✅
No pagination implemented	✅
No DDD / hexagonal architecture	✅
GitHub API used as data source	✅
Structured 404 error response	✅
README file provided	✅