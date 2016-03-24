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
package br.com.zup.controller.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.zup.controller.dao.PontoInteresseDao;
import br.com.zup.model.entity.PontoInteresse;
import br.com.zup.util.Constantes;

@Stateless
public class PontoInteresseBusinessImpl implements PontoInteresseBusiness {

    @EJB
    private PontoInteresseDao dao;

    @Override
    public void salvar(final PontoInteresse entity) throws Exception {
	if (entity != null && (entity.getCoordX() <= 0 || entity.getCoordY() <= 0)) {
	    throw new Exception(Constantes.MSG_ERR_NEGATIVO);
	}
	dao.salvar(entity);
    }

    @Override
    public List<PontoInteresse> listarPontos(final Integer posicaoInicial, final Integer maxResult,
	    final Integer coordX, final Integer coordY, final Integer dMax) {

	// filtra os pontos conforme o valor maximo da distancia, dada um
	// coordenada
	final List<PontoInteresse> result = dao.listarPontos(posicaoInicial, maxResult, coordX, coordY, dMax);

	List<PontoInteresse> filtrados = new ArrayList<>();
	if (coordX != null && coordY != null && dMax != null) {
	    // para cada item do resultado, deve-se saber se a distancia é menor
	    // igual a distancia maxima permitida.
	    for (PontoInteresse item : result) {
		if (calculaDistanciaPontos(item.getCoordX(), item.getCoordY(), coordX, coordY)
			.compareTo(Double.valueOf(dMax)) <= 0) {
		    filtrados.add(item);
		}
	    }
	} else {
	    filtrados = result;
	}

	return filtrados;
    }

    /**
     * Calcula a distancia entres os pontos: A(x1, y1) e B(x2, y2)
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return valor da distancia dos pontos
     */
    private Double calculaDistanciaPontos(final Integer x1, final Integer y1, final Integer x2, final Integer y2) {
	int catetoA = Math.abs(x1 - x2);
	int catetoB = Math.abs(y1 - y2);

	// calcula a hipotenusa para descobrir a distancia entre os pontos
	final Double hipotenusa = Math.sqrt(Math.pow(catetoA, 2) + Math.pow(catetoB, 2));
	return hipotenusa;
    }

    @Override
    public PontoInteresse getById(Long id) {
	return dao.getById(id);
    }

    @Override
    public void delete(Long id) {
	dao.delete(id);
    }

    public void setDao(final PontoInteresseDao dao) {
	this.dao = dao;
    }
    
}
