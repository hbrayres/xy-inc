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
package br.com.zup.contoller.business;

import java.util.List;

import javax.ejb.Local;

import br.com.zup.model.entity.PontoInteresse;

@Local
public interface PontoInteresseBusiness {

    void salvar(final PontoInteresse entity) throws Exception;

    PontoInteresse getById(final Long id);

    void delete(final Long id);

    List<PontoInteresse> listarPontos(final Integer posicaoInicial, final Integer maxResult, final Integer coordX,
	    final Integer coordY, final Integer dMax);
}
