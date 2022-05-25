package com.ficohsa.challenge.adn.mutant.service;


import com.ficohsa.challenge.adn.mutant.dto.Response.AdnMutantCheckResponse;
import com.ficohsa.challenge.adn.mutant.dto.Response.AdnMutantResponse;
import com.ficohsa.challenge.adn.mutant.dto.request.AdnMutantRequest;
import com.ficohsa.challenge.adn.mutant.entity.Stats;

import java.util.List;

/**
 * This is an interface for adn mutant
 *
 * @author Santiago Botero Madrid
 * @since 24.04.2022
 */
public interface AdnMutantService {

    AdnMutantCheckResponse isMutant(AdnMutantRequest adnMutantRequest);

    AdnMutantResponse stats();

    List<Stats> statsList();
}
