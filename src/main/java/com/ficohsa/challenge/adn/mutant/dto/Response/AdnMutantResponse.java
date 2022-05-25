package com.ficohsa.challenge.adn.mutant.dto.Response;


import com.fasterxml.jackson.annotation.JsonProperty;

public class AdnMutantResponse {
    @JsonProperty("count_mutant_dna")
    private Integer countMutant;
    @JsonProperty("count_human_dna")
    private Integer countHuman;
    @JsonProperty
    private Double ratio;

    public Integer getCountMutant() {
        return countMutant;
    }

    public void setCountMutant(Integer countMutant) {
        this.countMutant = countMutant;
    }

    public Integer getCountHuman() {
        return countHuman;
    }

    public void setCountHuman(Integer countHuman) {
        this.countHuman = countHuman;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}
