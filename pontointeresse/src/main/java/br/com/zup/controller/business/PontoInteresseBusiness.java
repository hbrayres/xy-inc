package br.com.zup.controller.business;

import java.util.List;

import javax.ejb.Local;

import br.com.zup.model.entity.PontoInteresse;

/**
 * Interface ejb.Local define os metodos acessíveis
 * 
 * @author heber.junior
 *
 */
@Local
public interface PontoInteresseBusiness {

    void salvar(final PontoInteresse entity) throws Exception;

    PontoInteresse getById(final Long id);

    void delete(final Long id);

    List<PontoInteresse> listarPontos(final Integer posicaoInicial, final Integer maxResult, final Integer coordX,
	    final Integer coordY, final Integer dMax);
}
