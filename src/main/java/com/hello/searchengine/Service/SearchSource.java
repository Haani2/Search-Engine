package com.hello.searchengine.Service;

import com.hello.searchengine.Model.Document;
import java.util.List;
public interface SearchSource {
        List<Document> search(String query);
        String getSourceName();

}
