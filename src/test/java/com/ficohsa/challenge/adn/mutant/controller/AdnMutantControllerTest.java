package com.ficohsa.challenge.adn.mutant.controller;

import com.ficohsa.challenge.adn.mutant.config.AdnMutantConfig;
import com.ficohsa.challenge.adn.mutant.dto.Response.AdnMutantCheckResponse;
import com.ficohsa.challenge.adn.mutant.dto.Response.AdnMutantResponse;
import com.ficohsa.challenge.adn.mutant.dto.request.AdnMutantRequest;
import com.ficohsa.challenge.adn.mutant.entity.Stats;
import com.ficohsa.challenge.adn.mutant.exceptions.BusinessException;
import com.ficohsa.challenge.adn.mutant.service.impl.AdnMutantServiceImpl;
import com.ficohsa.challenge.adn.mutant.util.AdnMutantUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AdnMutantConfig.class})
class AdnMutantControllerTest {

    @Spy
    @InjectMocks
    AdnMutantController adnMutantController;

    @Mock
    private AdnMutantServiceImpl adnMutantService;

    @Autowired
    private ApplicationContext applicationContext;

    private AdnMutantRequest adnMutantRequest;

    @BeforeEach
    void setUp() {
        adnMutantRequest = AdnMutantUtil.adnMutantRequestMock();
    }

    @Test
    void checkMutant() {
        when(adnMutantService.isMutant(adnMutantRequest)).thenReturn(AdnMutantUtil.AdnMutantCheckResponseMock());
        ResponseEntity<AdnMutantCheckResponse> response =  adnMutantController.checkMutant(adnMutantRequest);
        Assertions.assertNotNull(response);
    }

    @Test
    void checkStats() {
        when(adnMutantService.stats()).thenReturn(AdnMutantUtil.adnMutantStatsResponseMock());
        ResponseEntity<AdnMutantResponse> response =  adnMutantController.checkStats();
        Assertions.assertNotNull(response);
    }

    @Test
    void getListStats() {
        when(adnMutantService.statsList()).thenReturn(AdnMutantUtil.adnMutantResponseMock());
        ResponseEntity<List<Stats>>  response =  adnMutantController.getListStats();
        Assertions.assertNotNull(response);
    }

    @Test
    void getListStatsException() {
        when(adnMutantService.statsList()).thenThrow(BusinessException.class);
        Assertions.assertThrows(Exception.class, () -> adnMutantController.getListStats());
    }

    @Test
    void checkStatsException() {
        when(adnMutantService.stats()).thenThrow(BusinessException.class);
        Assertions.assertThrows(Exception.class, () -> adnMutantController.checkStats());
    }

    @Test
    void checkMutantException() {
        when(adnMutantService.isMutant(adnMutantRequest)).thenThrow(BusinessException.class);
        Assertions.assertThrows(Exception.class, () -> adnMutantController.checkMutant(adnMutantRequest));

    }
}