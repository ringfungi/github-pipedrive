package com.example.githubclient;

import org.eclipse.egit.github.core.Gist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GitHubClientController {
    @Autowired
    private GitHubService githubService;

    @GetMapping("/users/{username}/gists")
    public List<Gist> getGists(@PathVariable("username") String username) throws IOException {
        return githubService.getPublicGists(username);
    }
}