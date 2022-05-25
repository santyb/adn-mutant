package com.ficohsa.challenge.adn.mutant.controller;

import com.ficohsa.challenge.adn.mutant.dto.Response.AdnMutantCheckResponse;
import com.ficohsa.challenge.adn.mutant.dto.Response.AdnMutantResponse;
import com.ficohsa.challenge.adn.mutant.dto.request.AdnMutantRequest;
import com.ficohsa.challenge.adn.mutant.entity.Stats;
import com.ficohsa.challenge.adn.mutant.enums.ValidLetters;
import com.ficohsa.challenge.adn.mutant.exceptions.BusinessException;
import com.ficohsa.challenge.adn.mutant.service.impl.AdnMutantServiceImpl;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.ficohsa.challenge.adn.mutant.constants.AdnMutantConstants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * This is the Adn Mutant controller class.
 *
 * @author Santiago Botero Madrid
 * @since 23.05.2022
 */

@RestController
@RequestMapping(value = ADN_MUTANT_SERVICE)
public class AdnMutantController {

    @Autowired
    private AdnMutantServiceImpl adnMutantService;

    private final Logger logger = Logger.getLogger(this.getClass().getName());


    @PostMapping(value = CHECK_MUTANT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AdnMutantCheckResponse> checkMutant(@RequestBody AdnMutantRequest adnMutant) {

        logger.log(Level.INFO, "Entering into AdnMutantController.checkMutant()");
        AdnMutantCheckResponse adnMutantCheckResponse;
        try {
            char[] res = Arrays.stream(adnMutant.getDna())
                    .collect(Collectors.joining())
                    .toCharArray();
            for (int index = 0; index < res.length; index++) {
                ValidLetters.findValue(String.valueOf(res[index]));
            }
            adnMutantCheckResponse = adnMutantService.isMutant(adnMutant);
        } catch (Exception exception) {
            throw exception;

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(adnMutantCheckResponse);
    }

    @GetMapping(value = CHECK_STATS, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AdnMutantResponse> checkStats() {

        logger.log(Level.INFO, "Entering into AdnMutantController.checkMutant()");
        AdnMutantResponse adnMutantResponse;
        try {
            adnMutantResponse = adnMutantService.stats();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(adnMutantResponse);
    }

    @GetMapping(value = GET_LIST_STATS, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Stats>> getListStats() {

        logger.log(Level.INFO, "Entering into AdnMutantController.checkMutant()");
        List<Stats> statsList;
        try {
            statsList = adnMutantService.statsList();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());

        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(statsList);
    }
}