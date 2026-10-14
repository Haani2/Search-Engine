package com.hello.searchengine.Controller;

import com.hello.searchengine.Model.Document;
import com.hello.searchengine.Service.SearchService;
import com.hello.searchengine.Service.WikipediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;
    private final WikipediaService wikipediaService;  // ← add this field

    @Autowired
    public SearchController(SearchService searchService, WikipediaService wikipediaService) {  // ← add parameter
        this.searchService = searchService;
        this.wikipediaService = wikipediaService;  // ← add this line
    }

    @GetMapping("/search")
    public List<Document> searching(@RequestParam String q) {
        return searchService.search(q);
    }

    @GetMapping("/search/wikipedia")
    public List<Document> searchWikipedia(@RequestParam String q) {
        return wikipediaService.search(q);  // ← lowercase w
    }
}
