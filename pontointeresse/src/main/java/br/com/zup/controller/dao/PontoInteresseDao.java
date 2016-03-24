package br.com.zup.controller.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.zup.model.entity.PontoInteresse;

/**
 * Interface ejb.Local define os metodos de consulta 
 * e updates na base de dados
 * 
 * @author heber.junior
 *
 */
@Local
public interface PontoInteresseDao {

    void salvar(final PontoInteresse entity);

    PontoInteresse getById(final Long id);
    
    void delete(final Long id);
    
    List<PontoInteresse> listarPontos(final Integer posicaoInicial, final Integer maxResult, final Integer coordX,
	    final Integer coordY, final Integer dMax);
}
