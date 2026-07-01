package com.hello.searchengine.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Document {
    private int id;
    private String url;
    private String title;
    private String content;
    private int wordCount;
}
