package com.example.githubclient;

import org.eclipse.egit.github.core.Gist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
@Configuration
@EnableScheduling
public class CoreService {
    @Autowired
    private GitHubService gitHubService;
    @Autowired
    private PipedriveService pipedriveService;

    private String userName;

    @PostConstruct
    public void usernameGet (String username, String APIToken) throws IOException {

        userName = username;

        List<Gist> userGists;
        userGists = getGists(userName, APIToken);

        for (int i = 0; i < userGists.size(); i++) {
            System.out.println(userGists.get(i).getDescription());
            System.out.println(userGists.get(i).getComments());
            createDeal(userGists.get(i).getDescription(), userGists.get(i).getComments());
        }
    }
    List<Gist> getGists(String userName, String APIToken) throws IOException {
        return gitHubService.getPublicGists(userName, APIToken);
    }

    //@Scheduled(fixedDelay = 50000, initialDelay = 50000)
    void createDeal(String dealTitle, int org_id) throws IOException {

        this.pipedriveService.postNewDeal(dealTitle, org_id);

    }

}
