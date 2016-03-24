package br.com.zup.controller.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.zup.controller.business.PontoInteresseBusiness;
import br.com.zup.model.entity.PontoInteresse;

/**
 * Fachada ejb.Stateless chama a camada de negocio.
 * 
 * @author heber.junior
 *
 */
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
