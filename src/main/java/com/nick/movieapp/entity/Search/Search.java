package com.nick.movieapp.entity.Search;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;

public class Search {
    private final List<HashMap<String, String>> result;

    public Search(JsonNode json) {
        this.result = new ObjectMapper().convertValue(json, new TypeReference<List<HashMap<String, String>>>(){} );
    }


    public List<HashMap<String, String>> getResult() {
        return result;
    }
}
