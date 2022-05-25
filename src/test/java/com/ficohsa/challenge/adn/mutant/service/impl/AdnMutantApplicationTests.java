package com.ficohsa.challenge.adn.mutant.service.impl;

import com.ficohsa.challenge.adn.mutant.config.AdnMutantConfig;
import com.ficohsa.challenge.adn.mutant.dto.request.AdnMutantRequest;
import com.ficohsa.challenge.adn.mutant.entity.Stats;
import com.ficohsa.challenge.adn.mutant.repository.StatsRepo;
import com.ficohsa.challenge.adn.mutant.util.AdnMutantUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdnMutantConfig.class})
class AdnMutantApplicationTests {

    @Spy
    private StatsRepo statsRepo;

    @Spy
    @InjectMocks
    AdnMutantServiceImpl adnMutantService;

    @Autowired
    private ApplicationContext applicationContext;

    private AdnMutantRequest adnMutantRequest;
    private AdnMutantRequest adnHumanRequest;

    @BeforeEach
    void setUp() {
        adnMutantRequest = AdnMutantUtil.adnMutantRequestMock();
        adnHumanRequest = AdnMutantUtil.adnHumanRequestMock();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    void isHumanTest() {
        Stats stats = new Stats();
        stats.setMutant(1);
        List<Stats> statsList = new ArrayList<>();
        statsList.add(stats);
        when(statsRepo.saveAllAndFlush(statsList)).thenReturn(AdnMutantUtil.adnMutantResponseMock());
        Assertions.assertThrows(ResponseStatusException.class, () -> adnMutantService.isMutant(adnHumanRequest));
    }

    @Test
    void isMutantTest() {
        Stats stats = new Stats();
        stats.setMutant(1);
        List<Stats> statsList = new ArrayList<>();
        statsList.add(stats);
        when(statsRepo.saveAllAndFlush(statsList)).thenReturn(AdnMutantUtil.adnMutantResponseMock());
        var response= adnMutantService.isMutant(adnMutantRequest);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Mutant DNA" ,response.getMessage());

    }

    @Test
    void statsTests() {
        when(statsRepo.findAll()).thenReturn(AdnMutantUtil.adnMutantResponseMock());
        var response= adnMutantService.stats();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1 ,response.getCountHuman());
        Assertions.assertEquals(1 ,response.getCountMutant());
        Assertions.assertEquals(1.0 ,response.getRatio());

    }

    @Test
    void statsListTests() {
        when(statsRepo.findAll()).thenReturn(AdnMutantUtil.adnMutantResponseMock());
        var response= adnMutantService.statsList();
        Assertions.assertNotNull(response);
        Assertions.assertEquals("dna1" ,response.get(0).getAdnMutantRequest());
        Assertions.assertEquals(1 ,response.get(0).getMutant());
        Assertions.assertEquals(1L ,response.get(0).getId());

    }

}
