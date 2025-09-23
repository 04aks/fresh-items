package io.github.aks.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer {
    private final ObjectMapper objectMapper;
    public JsonSerializer(){
        objectMapper = new ObjectMapper();
    }
    public JsonNode jsonToNode(String json) throws JsonProcessingException {

        return objectMapper.readTree(json);
    }
    public <T> T fromJson(String json, Class<T> clazz){
        try{
            return objectMapper.readValue(json, clazz);
        }catch(JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize json to object " + e);
        }
    }
}
