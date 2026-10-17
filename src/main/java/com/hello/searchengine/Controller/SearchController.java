package com.hello.searchengine.Controller;

import com.hello.searchengine.Model.Document;
import com.hello.searchengine.Service.GithubService;
import com.hello.searchengine.Service.RedditService;
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
    private final WikipediaService wikipediaService;
//    private final RedditService redditService;
private final GithubService githubService;

    @Autowired
    public SearchController(SearchService searchService,
                            WikipediaService wikipediaService,
                            RedditService redditService ,GithubService githubService) {
        this.searchService = searchService;
        this.wikipediaService = wikipediaService;
//        this.redditService = redditService;
        this.githubService = githubService;

    }

    @GetMapping("/search")
    public List<Document> searching(@RequestParam String q) {
        return searchService.search(q);
    }
  //Wikipedia end point
    @GetMapping("/search/wikipedia")
    public List<Document> searchWikipedia(@RequestParam String q) {
        return wikipediaService.search(q);
    }

    // Git-hub new endpoint
    @GetMapping("/search/github")
    public List<Document> searchGithub(@RequestParam String q) {

        return githubService.search(q);
    }

//    @GetMapping("/search/reddit")
//    public List<Document> searchReddit(@RequestParam String q) {
//        return redditService.search(q);
//    }
}