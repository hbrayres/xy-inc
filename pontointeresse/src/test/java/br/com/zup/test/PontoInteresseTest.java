/*
 * Copyright (C) 2015 Samsung Electronics Co., Ltd. All rights reserved.
 *
 * Mobile Communication Division,
 * Digital Media & Communications Business, Samsung Electronics Co., Ltd.
 *
 * This software and its documentation are confidential and proprietary
 * information of Samsung Electronics Co., Ltd.  No part of the software and
 * documents may be copied, reproduced, transmitted, translated, or reduced to
 * any electronic medium or machine-readable form without the prior written
 * consent of Samsung Electronics.
 *
 * Samsung Electronics makes no representations with respect to the contents,
 * and assumes no responsibility for any errors that might appear in the
 * software and documents. This publication and the contents hereof are subject
 * to change without notice.
 */
package br.com.zup.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.zup.controller.business.PontoInteresseBusinessImpl;
import br.com.zup.controller.dao.PontoInteresseDao;
import br.com.zup.controller.facade.FacadeImpl;
import br.com.zup.model.entity.PontoInteresse;
import br.com.zup.util.Constantes;

@RunWith(MockitoJUnitRunner.class)
public class PontoInteresseTest {

    /**
     * Mock das consultas no banco de dados.
     */
    @Mock
    private PontoInteresseDao dao;
    
    private PontoInteresseBusinessImpl business;
    private FacadeImpl facade;
    
    /**
     * Inicializa os objetos a serem usados no teste.
     */
    @Before
    public void initializa() {
	facade = new FacadeImpl();
	business = new PontoInteresseBusinessImpl();
	business.setDao(dao);
	facade.setPontoBusiness(business);
    }
    
    /**
     * Salvar um ponto de interesse com sucesso.
     */
    @Test
    public void testSalvarPOISucesso() {
	final PontoInteresse poi = new PontoInteresse();
	poi.setId(1L);
	poi.setNome("POI - Sucesso");
	poi.setCoordX(1);
	poi.setCoordY(1);
	
	try {
	    facade.salvar(poi);
	    
	} catch (Exception ex) {
	    Assert.fail("Não é possível salvar ponto de interesse com valor de coordenada negativa");
	}
    }
    
    /**
     * Testar coordenada negativa.
     */
    @Test
    public void testSalvarCoordenadaNegativa() {
	final PontoInteresse poi = new PontoInteresse();
	poi.setId(1L);
	poi.setNome("POI - Falha");
	poi.setCoordX(-12);
	poi.setCoordY(12);
	
	try {
	    facade.salvar(poi);
	    Assert.fail("Não é possível salvar ponto de interesse com valor de coordenada negativa");
	    
	} catch (Exception ex) {
	    Assert.assertTrue(ex.getMessage().equals(Constantes.MSG_ERR_NEGATIVO));
	}
    }
    
    /**
     * Teste verifica se retorna um ponto de interesse proximo.
     */
    @Test
    public void testPOIProximas() {
	final List<PontoInteresse> pois = new ArrayList<>();
	final int coordX = 10, coordY = 20, dMax = 10;
	
	final PontoInteresse poi = new PontoInteresse();
	poi.setId(1L);
	poi.setNome("POI - 1");
	poi.setCoordX(12);
	poi.setCoordY(12);
	pois.add(poi);
	Mockito.when(dao.listarPontos(null, null, coordX, coordY, dMax)).thenReturn(pois);
	
	List<PontoInteresse> result = facade.listarPontos(null, null, coordX, coordY, dMax);
	Assert.assertEquals(pois, result);
    }
    
    /**
     * Testar lista um ponto distante
     */
    @Test
    public void testPOIDistantes() {
	final List<PontoInteresse> poisDistante = new ArrayList<>();
	final List<PontoInteresse> pois = new ArrayList<>();
	final int coordX = 10, coordY = 20, dMax = 10;
	
	final PontoInteresse poi = new PontoInteresse();
	poi.setId(1L);
	poi.setNome("POI - Longe");
	poi.setCoordX(28);
	poi.setCoordY(2);
	poisDistante.add(poi);
	Mockito.when(dao.listarPontos(null, null, coordX, coordY, dMax)).thenReturn(poisDistante);
	
	List<PontoInteresse> result = facade.listarPontos(null, null, coordX, coordY, dMax);
	Assert.assertEquals(pois, result);
    }
    
    /**
     * Listar todos os registros
     */
    @Test
    public void testListarTodosPOIs() {
	final List<PontoInteresse> pois = new ArrayList<>();
	
	PontoInteresse poi = new PontoInteresse();
	poi.setId(1L);
	poi.setNome("POI - 1");
	poi.setCoordX(12);
	poi.setCoordY(12);
	pois.add(poi);
	
	poi = new PontoInteresse();
	poi.setId(2L);
	poi.setNome("POI - 2");
	poi.setCoordX(15);
	poi.setCoordY(15);
	Mockito.when(dao.listarPontos(null, null, null, null, null)).thenReturn(pois);
	
	List<PontoInteresse> result = facade.listarPontos(null, null, null, null, null);
	Assert.assertEquals(pois, result);
    }
    
}
