package com.hello.searchengine;

public class TFScorer {
    public Double calculatorTf(Document doc,String term){
        String[] word = doc.getContent().split(" ");
        int count=0;
        for(String words:word){
            if(term.equals(words)){
                count++;
            }
        }
        double totalCount=doc.getWordCount();
        return count/totalCount;
    }
}
