package com.ficohsa.challenge.adn.mutant.dto.request;

import java.util.Arrays;

public class AdnMutantRequest {

    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }

    @Override
    public String toString() {
        return "dna={" + Arrays.toString(dna) +
                '}';
    }
}
