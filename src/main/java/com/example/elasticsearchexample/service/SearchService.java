package com.example.elasticsearchexample.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.HighlightField;
import co.elastic.clients.elasticsearch.core.search.HighlighterOrder;
import com.example.elasticsearchexample.entity.Indeces;
import com.example.elasticsearchexample.entity.dto.SearchResponseDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class SearchService {
    private final ElasticsearchClient client;
    private final Map<String, HighlightField> fieldsHighlight = new HashMap<>();

    public SearchService(ElasticsearchClient client) {
        this.client = client;
        fieldsHighlight.put("author_nickname", HighlightField.of(builder -> builder.fragmentSize(150)));
        fieldsHighlight.put("description", HighlightField.of(builder -> builder.fragmentSize(150)));
        fieldsHighlight.put("post_tags", HighlightField.of(builder -> builder.fragmentSize(150)));
        fieldsHighlight.put("user_firstname", HighlightField.of(builder -> builder.fragmentSize(150)));
        fieldsHighlight.put("user_lastname", HighlightField.of(builder -> builder.fragmentSize(150)));
        fieldsHighlight.put("user_nickname", HighlightField.of(builder -> builder.fragmentSize(150)));
        fieldsHighlight.put("user_bio", HighlightField.of(builder -> builder.fragmentSize(150)));
    }

    public List<SearchResponseDTO> search(Indeces[] indeces, String query) throws IOException {
        var indexes = getIndeces(indeces);
        SearchRequest searchRequest = SearchRequest.of(req -> req.index(indexes)
                .query(queryB -> queryB.multiMatch(multimatch ->
                        multimatch
                                .query(query)
                                .fuzziness("auto")))
                .highlight(highlight ->
                        highlight
                                .order(HighlighterOrder.Score)
                                .type("unified")
                                .requireFieldMatch(false)
                                .fields(fieldsHighlight)));
        SearchResponse<ObjectNode> response = client.search(searchRequest, ObjectNode.class);
        return response.hits().hits().stream()
                .map(SearchResponseDTO::toModel)
                .collect(Collectors.toList());
    }

    public List<SearchResponseDTO> searchWithFilters(Indeces[] indeces, String must, String mustNot) throws IOException {
        var indexes = getIndeces(indeces);
        var mustNotQuery = Query.of(q -> q.multiMatch(mq -> mq.query(mustNot)));
        var mustQuery = Query.of(q -> q.simpleQueryString(sq -> {
            sq.query(must);
            sq.defaultOperator(Operator.And);
            return sq;
        }));
        var myQuery = Query.of(query -> query.bool(boolQ -> {
            boolQ.must(mustQuery);
            boolQ.mustNot(mustNotQuery);
            return boolQ;
        }));

        var response = client.search(item ->
                item.index(indexes)
                        .query(myQuery)
                        .highlight(highlight ->
                                highlight
                                        .order(HighlighterOrder.Score)
                                        .type("unified")
                                        .fields(fieldsHighlight)), ObjectNode.class);
        return response.hits().hits().stream()
                .map(SearchResponseDTO::toModel)
                .collect(Collectors.toList());
    }

    private String getIndeces(Indeces[] indeces) {
        return Stream.of(indeces)
                .map(Indeces::toString)
                .collect(Collectors.joining(","));
    }
}
