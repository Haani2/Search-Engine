package com.hello.searchengine.Indexer;

import com.hello.searchengine.Model.Document;
import org.springframework.stereotype.Component;

@Component
public class TFScorer {
    public Double calculatorTF(Document doc, String term){
        String[] word = doc.getContent().split(" ");
        int count=0;
        for(String words:word){
            if(term.equalsIgnoreCase(words)){
                count++;
            }
        }
        double totalCount=doc.getWordCount();
        return count/totalCount;
    }
}
