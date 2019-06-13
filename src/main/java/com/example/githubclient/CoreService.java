package com.example.githubclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class CoreService {
    @Autowired
    private GitHubService gitHubService;
    @Autowired
    private PipedriveService pipedriveService;

    /*@PostConstruct
    void getGist() throws IOException {
        this.gitHubService.getPublicGists("ringfungi");
    }*/
    @PostConstruct
    void createDeal() throws IOException {
        int i = this.pipedriveService.postNewDeal();
        System.out.println(i + "\n");
    }

}
