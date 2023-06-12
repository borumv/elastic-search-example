package com.example.elasticsearchexample.entity;

import org.springframework.data.convert.WritingConverter;

public enum Indeces {
    COMMENT_INDEX("comment-index"),
    POST_INDEX("post-index"),
    USER_INDEX("users-index");
    private final String index;
    Indeces(final String index) {
        this.index = index;
    }
    @Override
    public String toString() {
        return index;
    }
}
