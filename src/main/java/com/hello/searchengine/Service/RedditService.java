package com.hello.searchengine.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.searchengine.Model.Document;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedditService implements SearchSource{
    private static final String SEARCH_URL =
            "https://www.reddit.com/search.json?limit=10&q=";

    private final RestTemplate restTemplate;

    public RedditService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getSourceName() {
        return "Reddit";
    }

    @Override
    public List<Document> search(String query) {
        String url = SEARCH_URL + query;

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "SearchEngine/1.0 (hani@example.com)");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, String.class
        );

        List<Document> results = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode searchResults = root.path("data").path("children") ;

            int id = 1;
            for (JsonNode item : searchResults) {

                JsonNode data = item.path("data");
                String title   = data.path("title").asText();
                String snippet = data.path("selftext").asText();
                String postUrl = data.path("url").asText();
                int wordCount  = snippet.split(" ").length;

                results.add(new Document(id++, postUrl, title, snippet, wordCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
