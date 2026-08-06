package com.hello.searchengine.Indexer;

import com.hello.searchengine.Model.Document;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class Indexer {

    @Getter
    private HashMap<String, List<Integer>> invertedIndex=new HashMap<>();
    @Getter
    private HashMap<Integer, Document> documentStore = new HashMap<>();

    @PostConstruct
    public void init() {
        List<Document> documents = new ArrayList<>();
        documents.add(new Document(1, "url/1", "Java Basics", "java interview tips", 3));
        documents.add(new Document(2, "url/2", "Python Guide", "python interview basics", 3));
        documents.add(new Document(3, "url/3", "Spring Boot", "java spring boot tutorial", 4));
        buildIndex(documents);

    }
    public void buildIndex(List<Document> documents){

        for(Document doc:documents){
            documentStore.put(doc.getId(),doc);
           String content=doc.getContent();
           String[] word=content.split(" ");

           for(String sc:word) {
               List<Integer> docList = invertedIndex.getOrDefault(sc.toLowerCase(), new ArrayList<>());
               docList.add(doc.getId());
               invertedIndex.put(sc.toLowerCase(), docList);
           }
        }


    }
}
