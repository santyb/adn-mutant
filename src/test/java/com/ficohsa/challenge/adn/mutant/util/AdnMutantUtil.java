package com.ficohsa.challenge.adn.mutant.util;

import com.ficohsa.challenge.adn.mutant.dto.Response.AdnMutantCheckResponse;
import com.ficohsa.challenge.adn.mutant.dto.Response.AdnMutantResponse;
import com.ficohsa.challenge.adn.mutant.dto.request.AdnMutantRequest;
import com.ficohsa.challenge.adn.mutant.entity.Stats;

import java.util.ArrayList;
import java.util.List;

public class AdnMutantUtil {

    private AdnMutantUtil() {
    }

    public static AdnMutantRequest adnMutantRequestMock() {
        AdnMutantRequest adnMutantRequest = new AdnMutantRequest();
        String[] dna = {
                "ATTCGA",
                "ACACTT",
                "ATATGG",
                "AGATGA",
                "CCGTAG",
                "TCATTG"};

        adnMutantRequest.setDna(dna);
        return adnMutantRequest;
    }

    public static AdnMutantRequest adnHumanRequestMock() {
        AdnMutantRequest adnMutantRequest = new AdnMutantRequest();
        String[] dna = {
                "ATTCGA",
                "CCACTT",
                "TTATGG",
                "AGATGA",
                "CCGCAG",
                "TCATTG"};

        adnMutantRequest.setDna(dna);
        return adnMutantRequest;
    }

    public static List<Stats> adnMutantResponseMock() {
        List<Stats> statsList = new ArrayList<>();

        Stats stats = new Stats();
        stats.setAdnMutantRequest("dna1");
        stats.setMutant(1);
        stats.setId(1L);

        Stats stats2 = new Stats();
        stats2.setAdnMutantRequest("dna2");
        stats2.setMutant(0);
        stats2.setId(2L);

        statsList.add(stats);
        statsList.add(stats2);
        return statsList;
    }

    public static AdnMutantResponse adnMutantStatsResponseMock() {
        AdnMutantResponse adnMutantResponse = new AdnMutantResponse();

        adnMutantResponse.setCountMutant(1);
        adnMutantResponse.setCountHuman(1);
        adnMutantResponse.setRatio(1.0);
        return adnMutantResponse;
    }

    public static AdnMutantCheckResponse AdnMutantCheckResponseMock() {
        AdnMutantCheckResponse adnMutantCheckResponse = new AdnMutantCheckResponse();

        adnMutantCheckResponse.setMessage("Contoller Test");
        return adnMutantCheckResponse;
    }

}
