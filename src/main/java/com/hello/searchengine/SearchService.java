package com.hello.searchengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchService {
    private Indexer indexer;
     public SearchService(Indexer indexer){
         this.indexer=indexer;
     }
     public List<Integer> search(String query){

         HashMap<String, List<Integer>> map=indexer.getInvertedIndex();
       String[] word=query.split(" ");
       List<Integer> result=null;
       for(String words:word){
           List<Integer> doclist=map.getOrDefault(words, new ArrayList<>());
           if(result==null){
               result=new ArrayList<>(doclist);
           }
           else {
               result.retainAll(doclist);
           }
       }
       return result;
     }
}
