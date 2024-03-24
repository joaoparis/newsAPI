package pt.demos.news.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class News {
    @JsonProperty
    String title;

    public String getTitle() {
        return title;
    }

    @JsonProperty
    String description;

    @JsonProperty
    String journalist;
    
    @JsonProperty
    Date date;
}
