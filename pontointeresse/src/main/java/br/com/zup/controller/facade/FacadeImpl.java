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
package br.com.zup.controller.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.zup.contoller.business.PontoInteresseBusiness;
import br.com.zup.model.entity.PontoInteresse;

@Stateless
public class FacadeImpl implements FacadeLocal {

    @EJB
    private PontoInteresseBusiness pontoBusiness;
    
    @Override
    public void salvar(final PontoInteresse entity) throws Exception {
	pontoBusiness.salvar(entity);
    }

    @Override
    public List<PontoInteresse> listarPontos(Integer posicaoInicial, Integer maxResult, Integer coordX,
	    Integer coordY, final Integer dMax) {
	return pontoBusiness.listarPontos(posicaoInicial, maxResult, coordX, coordY, dMax);
    }

    @Override
    public PontoInteresse getPontoInteresseById(final Long id) {
	return pontoBusiness.getById(id);
    }

    @Override
    public void deletePontoInteresse(final Long id) {
	pontoBusiness.delete(id);
    }
    
    public void setPontoBusiness(final PontoInteresseBusiness pontoBusiness) {
	this.pontoBusiness = pontoBusiness;
    }

}
