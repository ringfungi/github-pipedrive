# DevOps Challenge - A Test Task for Pipedrive

This project uses both Github's and Pipedrive's APIs to:
1. Query a given user's gists
2. Create a deal in Pipedrive for each of those gists
3. Periodically check for new gists using a web endpoint

## Part I
Back end:
It was developed using Retrofit, an HTTP client library, to define interfaces for REST API methods; Spring Boot to create a client for a RESTful API and Java.
To make Gradle run the app:
```
./gradlew bootRun

```

Front end:
It was developed using vanilla JS, HTML and CSS. The simple UI includes field to insert a Github token, which with the **Set key** button can be saved to local storage for later use or for making the next calls. The **Last used** button reads the last token used to make calls and places it on the token input field.

The Github username field accepts a username to retrieve available public gists for that user and also creates a Pipedrive deal for each gist. The username will be saved on **lastSeen** for periodic checks (every three hours) to see if any new gists have been created since the last visit; if so, **updated gist** will display a message.

## Part II

The app is in a **Docker container** and managed by **Kubernetes** on **Google Kubernetes Engine cluster**. This makes the app easily scalable, easy to manage and update. The details and processes of the deployment can be seen at GCP [Documentation](https://cloud.google.com/kubernetes-engine/docs/tutorials/hello-app) section.

Some details of about the Kubernetes Engine used:
* Number of nodes - 2
* Total cores - 2 vCPUs
* Total memory - 7.50 GB
* Server location - europe-west2-b
* Custom firewall rules [x]
* Load balancer	 [x]
* Static IP reserved [x]

### Prerequisites

To build, run and test this project on your machine, you should use:

```

*JDK 8 or greater (JDK 11 was used).
*A mature IDE of your choice (i.e., IntelliJ, Netbeans, or Eclipse).
*An external HTTP client like curl, Postman, etc.
*A GitHub account.

```

## Testing

To test the app, either build and run this repo and access it on http://localhost:8080 or access the deployed app at http://35.234.159.163/

## Known issues

Due to time constraints, the Pipedrive account in which the gists are created is hardcoded. Please email me at ringfungi@gmail.com for access to the account.

## Authors

* **Guilherme Lopes** - [ringfungi](https://github.com/ringfungi)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

[This article](https://auth0.com/blog/developing-a-restful-client-with-retrofit-and-spring-boot/) helped me set up the back end.
