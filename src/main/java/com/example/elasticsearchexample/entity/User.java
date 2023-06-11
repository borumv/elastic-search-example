package com.example.elasticsearchexample.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "users-index")
public class User {
    @Id
    @Field(type = FieldType.Integer, name = "user_id")
    private Long userId;

    @Field(type = FieldType.Text, name = "user_nickname")
    private String nickName;
    @Field(type = FieldType.Text, name = "user_firstname")
    private String firstName;
    @Field(type = FieldType.Text, name = "user_lastname")
    private String lastName;
    @Field(type = FieldType.Text, name = "user_bio")
    private String biography;
}
