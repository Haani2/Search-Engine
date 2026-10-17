package com.hello.searchengine.Service;

import com.hello.searchengine.Model.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.*;
import java.util.ArrayList;

import java.util.List;

@Service
public class GithubService implements SearchSource {

    private static final String SEARCH_URL =
            "https://api.github.com/search/repositories?per_page=10&q=";

    //    \\@Value("${github.token}")
   // private String githubToken;

    private final RestTemplate restTemplate;

    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getSourceName() {
        return "GitHub";
    }

    @Override
    public List<Document> search(String query) {
        String url = SEARCH_URL + query;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "SearchEngine/1.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, String.class
        );

        List<Document> results = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode items = root.path("items");

            int id = 1;
            for (JsonNode item : items) {
                String title   = item.path("full_name").asText();
                String snippet = item.path("description").asText();
                String repoUrl = item.path("html_url").asText();
                int stars      = item.path("stargazers_count").asInt();

                results.add(new Document(id++, repoUrl, title, snippet, stars));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
