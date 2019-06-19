package com.example.githubclient;

import org.eclipse.egit.github.core.Gist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
@Configuration
@EnableScheduling
public class CoreService {
    @Autowired
    private GitHubService gitHubService;
    @Autowired
    private PipedriveService pipedriveService;

    private int i = 0;
    private String userName;

    @PostConstruct
    public void usernamePrompt () throws IOException {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter a GitHub username to check for its public gists:");

        userName = myObj.nextLine();
        System.out.println("Username: " + userName);

        List<Gist> userGists;
        userGists = getGists(userName);

        for (int i = 0; i < userGists.size(); i++) {
            System.out.println(userGists.get(i).getDescription());
            System.out.println(userGists.get(i).getComments());
            createDeal(userGists.get(i).getDescription(), userGists.get(i).getComments());
        }
    }
    List<Gist> getGists(String userName) throws IOException {
        return gitHubService.getPublicGists(userName);
    }

    //@Scheduled(fixedDelay = 50000, initialDelay = 50000)
    void createDeal(String dealTitle, int org_id) throws IOException {

        this.pipedriveService.postNewDeal(dealTitle, org_id);

    }

}
