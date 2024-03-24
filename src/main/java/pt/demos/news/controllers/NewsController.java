package pt.demos.news.controllers;

import org.springframework.web.bind.annotation.RestController;

import pt.demos.news.models.News;
import pt.demos.news.services.INewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class NewsController {
    
    @Autowired
    private INewsService newsService;

    NewsController(final INewsService newsService){
        this.newsService = newsService;
    }

    @PostMapping("news")
    @Validated
    public ResponseEntity<String> requestMethodName(@RequestBody News body) {
        System.out.println("New news received...");
        var response = newsService.handleNews(body);
        System.out.println("News was handled!");
        return response;
    }

}
