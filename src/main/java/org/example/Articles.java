package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.Timestamp;

public class Articles {
    @JsonProperty("title")
    private String title;
    @JsonProperty("num_comments")
    private Integer numComments;
    @JsonProperty("story_title")
    private String storyTitle;


    public Articles() {
    }

    public String getTitle() {
        return title;
    }

    public Integer getNumComments() {
        return numComments;
    }


    public String getStoryTitle() {
        return storyTitle;
    }

}
