package com.example.elasticsearchexample.converters;

import com.example.elasticsearchexample.entity.Indeces;
import org.springframework.core.convert.converter.Converter;

public class StringToIndecesConverter implements Converter<String, Indeces> {
    @Override
    public Indeces convert(String source) {
        return switch (source) {
            case "comment-index" -> Indeces.COMMENT_INDEX;
            case "post-index" -> Indeces.POST_INDEX;
            case "users-index" -> Indeces.USER_INDEX;
            default -> null;
        };
    }
}
