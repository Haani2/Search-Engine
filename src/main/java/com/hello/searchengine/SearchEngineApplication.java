package com.hello.searchengine;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SearchEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchEngineApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST");
            }
        };
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }
    //        Document doc = new Document(1,"url/1","java","Java interview",4);
//        Document doc1 = new Document(2,"url/2","python","first python code",5);
//        Document doc2 = new Document(3,"url/3","c++","c++ programing ",3);
//        List<Document> documents=new ArrayList<>();
//        documents.add(doc);
//        documents.add(doc1);
//        documents.add(doc2);
//
//        Indexer indexer=new Indexer();
//        indexer.buildIndex(documents);
//
//        SearchService searchService = new SearchService(indexer);
//       List<Integer>result=searchService.search("Java");
//        System.out.println(result);
//
//        TFScorer tfScorer=new TFScorer();
//        System.out.println(tfScorer.calculatorTF(doc,"Java"));

}