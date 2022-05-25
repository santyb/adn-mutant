package com.ficohsa.challenge.adn.mutant.enums;

import java.util.Arrays;
import java.util.Optional;

public enum ValidLetters {

    A("A"),
    T("T"),
    C("C"),
    G("G");

    private String validLetters;

    public String getValidLetters() {
        return validLetters;
    }


    ValidLetters(String validLetters) {
        this.validLetters = validLetters;
    }

    public static ValidLetters findValue(String letter) {
        Optional<ValidLetters> validLetterType = Arrays.stream(ValidLetters.values())
                .filter(v -> v.validLetters.equalsIgnoreCase(letter))
                .findFirst();
        return validLetterType.orElseThrow();
    }
}
