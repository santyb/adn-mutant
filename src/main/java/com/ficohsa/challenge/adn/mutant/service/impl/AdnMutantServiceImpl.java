package com.ficohsa.challenge.adn.mutant.service.impl;

import com.ficohsa.challenge.adn.mutant.dto.Response.AdnMutantCheckResponse;
import com.ficohsa.challenge.adn.mutant.dto.Response.AdnMutantResponse;
import com.ficohsa.challenge.adn.mutant.dto.request.AdnMutantRequest;
import com.ficohsa.challenge.adn.mutant.entity.Stats;
import com.ficohsa.challenge.adn.mutant.exceptions.BusinessException;
import com.ficohsa.challenge.adn.mutant.repository.StatsRepo;
import com.ficohsa.challenge.adn.mutant.service.AdnMutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static com.ficohsa.challenge.adn.mutant.constants.AdnMutantConstants.MUTANT;

@Service
public class AdnMutantServiceImpl implements AdnMutantService {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private Integer contMutant = 0;

    private final List<String> adnList = new ArrayList<>(Arrays.asList(MUTANT));

    @Autowired
    private StatsRepo statsRepo;

    @Override
    public AdnMutantCheckResponse isMutant(AdnMutantRequest adnMutantRequest) {
        logger.log(Level.INFO, "Entering into AdnMutantServiceImpl.isMutant()");

        String[] requestDna = adnMutantRequest.getDna();
        contMutant = 0;

        AdnMutantCheckResponse adnMutantCheckResponse = new AdnMutantCheckResponse();
        try {
            if (requestDna[0].length() != requestDna.length) {
                throw new BusinessException("Datos No validos");
            }
            String[][] dnaMatrix = new String[requestDna[0].length()][requestDna.length];

            IntStream.range(0, requestDna.length)
                    .forEach(
                            i -> {
                                String row = requestDna[i];
                                isMutantCheck(requestDna[i]);
                                IntStream.range(0, row.length()).forEach(j -> dnaMatrix[i][j] = row.substring(j, j + 1));
                            });

            getVerticalValues(dnaMatrix);
            getDiagonalValues(dnaMatrix);

            Stats stats = new Stats();
            stats.setAdnMutantRequest(adnMutantRequest.toString());
            stats.setMutant(contMutant > 1 ? 1 : 0);
            saveMutant(stats);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, "Exiting into AdnMutantServiceImpl.isMutant()" + contMutant);
        if (contMutant < 1) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Human DNA");
        } else {
            adnMutantCheckResponse.setMessage("Mutant DNA");
        }

        return adnMutantCheckResponse;
    }

    @Override
    public AdnMutantResponse stats() {
        AdnMutantResponse adnMutantResponse = new AdnMutantResponse();
        Integer countMutant = 0;
        Integer countHuman = 0;

        List<Stats> statsList = statsRepo.findAll();

        if (!statsList.isEmpty()) {
            for (Stats statsItem : statsList) {
                if (statsItem.getMutant() != 0) {
                    countMutant++;
                } else {
                    countHuman++;
                }

            }
        }
        adnMutantResponse.setCountHuman(countHuman);
        adnMutantResponse.setCountMutant(countMutant);
        Double ratio = countHuman > 0 ? Double.valueOf(countMutant) / Double.valueOf(countHuman) : 0.0;
        adnMutantResponse.setRatio(ratio);
        return adnMutantResponse;
    }

    @Override
    public List<Stats> statsList() {
        return statsRepo.findAll();
    }

    private void saveMutant(Stats stats) {
        statsRepo.saveAndFlush(stats);
    }

    public void isMutantCheck(String adn) {

        adnList.forEach(x -> {
            if (adn.contains(x))
                contMutant++;
        });
    }

    public void getVerticalValues(String[][] matrix) {
        String letter;

        for (int x = 0; x < matrix.length; x++) {
            String word = "";
            for (int y = 0; y < matrix.length; y++) {
                letter = matrix[y][x];
                word = word + letter;
            }
            isMutantCheck(word);
        }
    }

    public void getDiagonalValues(String[][] matrix) {
        String letter;
        Integer height = matrix.length;
        Integer width = matrix[0].length;
        for (Integer diag = 1 - width; diag <= height - 1; diag += 1) {
            String wordColumn = "";
            for (Integer vert = Math.max(0, diag), hor = -Math.min(0, diag); vert < height && hor < width; vert += 1, hor += 1) {
                letter = matrix[vert][hor];
                wordColumn = wordColumn + letter;
            }
            if (wordColumn.length() > 3) {
                isMutantCheck(wordColumn);
            }
        }

    }

}
