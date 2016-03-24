package br.com.zup.controller.facade;

import java.util.List;

import javax.ejb.Local;

import br.com.zup.model.entity.PontoInteresse;

/**
 * Interface ejb.Local com metodos acessíveis.
 * @author heber.junior
 *
 */
@Local
public interface FacadeLocal {

    void salvar(final PontoInteresse entity) throws Exception;

    PontoInteresse getPontoInteresseById(final Long id);

    void deletePontoInteresse(final Long id);

    List<PontoInteresse> listarPontos(final Integer posicaoInicial, final Integer maxResult, final Integer coordX,
	    final Integer coordY, final Integer dMax);

}
