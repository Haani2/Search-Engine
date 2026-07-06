package com.hello.searchengine.Service;

import com.hello.searchengine.Indexer.Indexer;
import com.hello.searchengine.Model.Document;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class SearchService {
    private Indexer indexer;
     public SearchService(Indexer indexer){
         this.indexer=indexer;
     }


     public List<Document> search(String query){
         HashMap<Integer,Document> documentList= indexer.getDocumentStore();
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
         List<Document> documents= new ArrayList<>();
         for (int i=0;result!=null && i<result.size();i++) {
             documents.add(documentList.get(result.get(i)));
         }
         return documents;
     }
}
