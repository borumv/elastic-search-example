package com.example.elasticsearchexample.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(indexName = "post-index" )
public class Post {
    @Id
    @Field(type = FieldType.Integer, name = "post_id")
    private Long postId;

    @Field(type = FieldType.Integer, name = "message")
    private Integer authorId;
    @Field(type = FieldType.Integer, name = "author_nickname")
      private String authorNickname;
    @Field(type = FieldType.Text, name = "description")
    private String description;
    @Field(type = FieldType.Text, name = "post_tags")
    private List<String> tags;
    @Field(type = FieldType.Date, name = "created_at")
    private LocalDate createAt;
}
