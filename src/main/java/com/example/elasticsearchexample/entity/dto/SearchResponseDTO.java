package com.example.elasticsearchexample.entity.dto;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class SearchResponseDTO {
    String index;
    String id;
    Double score;
    ObjectNode source;
    Map<String, List<String>> highlights;
    public static SearchResponseDTO toModel(Hit<?> hit) {
        SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
        searchResponseDTO.setId(hit.id());
        searchResponseDTO.setIndex(hit.index());
        searchResponseDTO.setScore(hit.score());
        searchResponseDTO.setHighlights(hit.highlight());
        assert hit.source() != null;
        searchResponseDTO.setSource(((ObjectNode) hit.source()).deepCopy());
        System.out.println();
        return searchResponseDTO;
    }
}
