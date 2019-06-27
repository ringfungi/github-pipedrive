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

    @GetMapping("/newgists/{username}/{api_token}")
    public List<Gist> getNewGists(@PathVariable("username") String username,
                                  @PathVariable("api_token") String api_token) throws IOException {
        return githubService.getNewPublicGists(username, api_token);
    }
}