package com.hello.searchengine.Service;

import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class wikipediaService {

    private String URL = "https://en.wikipedia.org/wiki/${title}";

}
