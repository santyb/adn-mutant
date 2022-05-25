package com.ficohsa.challenge.adn.mutant.dto.Response;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AdnMutantCheckResponse {

    @JsonProperty
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
