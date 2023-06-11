package com.example.elasticsearchexample.entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Data
@Document(indexName = "comment-index")
public class Comment {
    @Id
    @Field(type = FieldType.Integer, name = "comment_id")
    private Long commentId;
    @Field(type = FieldType.Integer, name = "author_id")
    private Long authorId;
    @Field(type = FieldType.Integer, name = "post_id")
    private Long postId;
    @Field(type = FieldType.Keyword, name = "author_nickname")
    private String authorNickname;
    @Field(type = FieldType.Text, name = "description")
    private String description;
    @Field(type = FieldType.Date, name = "created_at")
    private LocalDate createAt;
}
