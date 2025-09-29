package io.github.aks.api;

import java.util.HashMap;
import java.util.Map;

public class AuthProvider {
    private final Map<String, String> header = new HashMap<>();
    public AuthProvider(String apiKey){
        header.put("API-Key", apiKey);
    }

    public Map<String, String> getAuthHeader(){
        return header;
    }
}
