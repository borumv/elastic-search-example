package com.example.elasticsearchexample.controller;

import com.example.elasticsearchexample.entity.Indeces;
import com.example.elasticsearchexample.entity.dto.SearchResponseDTO;
import com.example.elasticsearchexample.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService service;

    public SearchController(SearchService service) {
        this.service = service;
    }

    @GetMapping()
    public List<SearchResponseDTO> search(@RequestParam Indeces[] indeces, @RequestParam(value = "q", required = false) String query) throws IOException {
        return service.search(indeces, query);
    }

    @GetMapping("/filter")
    public List<SearchResponseDTO> searchWithFilters(@RequestParam Indeces[] indeces,
                                                     @RequestParam(value = "SEARCH (with AND)", defaultValue = "") String must,
                                                     @RequestParam(value = "NOT", defaultValue = "") String mustNot) throws IOException {
        return service.searchWithFilters(indeces, must, mustNot);
    }

}
