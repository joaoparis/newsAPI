package pt.demos.news.services;

import org.springframework.http.ResponseEntity;

import pt.demos.news.models.News;

public interface INewsService {

    public ResponseEntity<String> handleNews(News body);

}
