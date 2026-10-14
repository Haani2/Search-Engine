package com.hello.searchengine.Service;

import com.hello.searchengine.Model.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WikipediaService {

    private static final String SEARCH_URL =
            "https://en.wikipedia.org/w/api.php?action=query&list=search&format=json&srlimit=10&srsearch=";

    private final RestTemplate restTemplate;


    public WikipediaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
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
            JsonNode searchResults = root.path("query").path("search");

            int id = 1;
            for (JsonNode item : searchResults) {
                String title = item.path("title").asText();
                String snippet = item.path("snippet").asText()
                        .replaceAll("<[^>]*>", ""); // removes HTML tags
                String wikiUrl = "https://en.wikipedia.org/wiki/"
                        + title.replace(" ", "_");
                int wordCount = item.path("wordcount").asInt();

                results.add(new Document(id++, wikiUrl, title, snippet, wordCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
