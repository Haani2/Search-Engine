package com.hello.searchengine;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Indexer {
    @Getter
    private HashMap<String, List<Integer>> invertedIndex=new HashMap<>();
    public void buildIndex(List<Document> documents){
        for(Document doc:documents){
           String content=doc.getContent();
           String[] word=content.split(" ");
           for(String sc:word){
               List<Integer> docList=invertedIndex.getOrDefault(sc,new ArrayList<>());
               docList.add(doc.getId());
               invertedIndex.put(sc,docList);
           }
        }
    }
}
