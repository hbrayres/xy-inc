package br.com.zup.controller.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.zup.model.entity.PontoInteresse;

/**
 * Classe ejb.Stateless que implementa ao DAO de Ponto de Interesse 
 * 
 * @author heber.junior
 *
 */
@Stateless
public class PontoInteresseDaoImpl implements PontoInteresseDao {

    @PersistenceContext(unitName = "pontointeresse")
    private EntityManager em;

    @Override
    public void salvar(final PontoInteresse entity) {
	if (entity.getId() != null) {
	    if (getById(entity.getId()) == null) {
		throw new NoResultException();
	    }
	    em.merge(entity);
	} else {
	    em.persist(entity);
	}
    }

    @Override
    public List<PontoInteresse> listarPontos(final Integer posicaoInicial, final Integer maxResult, final Integer coordX,
	    final Integer coordY, final Integer dMax) {
	
	final StringBuilder hql = new StringBuilder();
	hql.append(" SELECT DISTINCT p FROM PontoInteresse p ");
	
	if (coordX != null && coordY != null && dMax != null) {
	    /*
	     * filtra os registros com base na distancia maxima 
	     * exigida de um ponto a outro
	     */
	    hql.append(" WHERE p.coordX >= :coordXMin ")
	    	.append(" AND p.coordX <= :coordXMax ")
	    	.append(" AND p.coordY >= :coordYMin ")
	    	.append(" AND p.coordY <= :coordYMax ");
	}
	
	hql.append(" ORDER BY p.nome ");
	
	final TypedQuery<PontoInteresse> findAllQuery = em
		.createQuery(hql.toString(), PontoInteresse.class);
	
	if (posicaoInicial != null) {
	    findAllQuery.setFirstResult(posicaoInicial);
	}
	if (maxResult != null) {
	    findAllQuery.setMaxResults(maxResult);
	}
	
	if (coordX != null && coordY != null && dMax != null) {
	    findAllQuery.setParameter("coordXMin", coordX - dMax);
	    findAllQuery.setParameter("coordXMax", coordX + dMax);
	    findAllQuery.setParameter("coordYMin", coordY - dMax);
	    findAllQuery.setParameter("coordYMax", coordY + dMax);
	}
	
	final List<PontoInteresse> results = findAllQuery.getResultList();
	return results;
    }

    @Override
    public PontoInteresse getById(final Long id) {
	return em.find(PontoInteresse.class, id);
    }

    @Override
    public void delete(final Long id) {
	final PontoInteresse en = em.find(PontoInteresse.class, id);
	if (en == null) {
	    throw new NoResultException();
	}
	em.remove(en);
    }

}
